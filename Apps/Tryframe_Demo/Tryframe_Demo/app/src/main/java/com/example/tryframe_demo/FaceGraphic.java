package com.example.tryframe_demo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.example.tryframe_demo.camera.GraphicOverlay;
import com.google.android.gms.vision.face.Face;

class FaceGraphic extends GraphicOverlay.Graphic {
  private final Context context;
  private boolean mIsFrontFacing;
  Resources resources;
  PointF detectPosition,detectLeftEyePosition,detectRightEyePosition,detectNoseBasePosition;
  PointF detectMouthLeftPosition,detectMouthBottomPosition,detectMouthRightPosition;
  PointF detectRightCheekPosition,detectLeftCheekPosition,detectRightEarPosition;
  PointF detectLeftEarPosition;
  Face face;
  int flag=0;
  float eulerZ,eulerY;
  // This variable may be written to by one of many threads. By declaring it as volatile,
  // we guarantee that when we read its contents, we're reading the most recent "write"
  // by any thread.
  private volatile FaceData mFaceData;
  private Paint mHintTextPaint;
  private Paint mHintOutlinePaint,mOutlinePaint;
  private Drawable mGlassesGraphic;
  private float width,height;
  FaceGraphic(GraphicOverlay overlay, Context context, boolean isFrontFacing) {
    super(overlay);
    mIsFrontFacing = isFrontFacing;
    this.resources = context.getResources();
    initializePaints(resources);
    initializeGraphics(resources);
    this.context=context;
  }
  private void initializeGraphics(Resources resources) {
    this.resources=resources;
      mGlassesGraphic = resources.getDrawable(R.drawable.glass,null);
  }
  void update(FaceData faceData,Face face) {
    this.face=face;
    mFaceData = faceData;
    postInvalidate(); // Trigger a redraw of the graphic (i.e. cause draw() to be called).
  }
  private void initializePaints(Resources resources) {
    mHintTextPaint = new Paint();
    mHintTextPaint.setColor(resources.getColor(R.color.colorPrimary));
    //mHintTextPaint.setColor(Color.parseColor("#ff0000"));
    mHintTextPaint.setTextSize(resources.getDimension(R.dimen.textSize));
    mHintOutlinePaint = new Paint();
    mHintOutlinePaint.setColor(resources.getColor(R.color.overlayHint));
    mHintOutlinePaint.setStyle(Paint.Style.STROKE);
    mHintOutlinePaint.setStrokeWidth(resources.getDimension(R.dimen.hintStroke));
    mOutlinePaint = new Paint();
    mOutlinePaint.setColor(resources.getColor(R.color.outline));
    mOutlinePaint.setStyle(Paint.Style.STROKE);
    mOutlinePaint.setStrokeWidth(resources.getDimension(R.dimen.hintStroke));
  }
  @Override
  public void draw(Canvas canvas) {
     eulerY = mFaceData.getEulerY();
     eulerZ = mFaceData.getEulerZ();
// Draw the hat only if the subject's head is titled at a sufficiently jaunty angle.
      final float HEAD_TILT_HAT_THRESHOLD = 1.5f;
      if (eulerZ >-0.3f && eulerZ < HEAD_TILT_HAT_THRESHOLD) {
          flag=1;
      }
      else
        flag=0;
    // Confirm that the face and its features are still visible before drawing any graphics over it.
    if (mFaceData == null) {
      Log.d("3DTry_On","##########mFaceData Null");
      return;
    }
    // 1
    detectPosition = mFaceData.getPosition();
    detectLeftEyePosition = mFaceData.getLeftEyePosition();
    detectRightEyePosition = mFaceData.getRightEyePosition();
    detectNoseBasePosition = mFaceData.getNoseBasePosition();
    detectMouthLeftPosition = mFaceData.getMouthLeftPosition();
    detectMouthBottomPosition = mFaceData.getMouthBottomPosition();
    detectMouthRightPosition = mFaceData.getMouthRightPosition();
    detectRightCheekPosition = mFaceData.getRightCheekPosition();
    detectLeftCheekPosition = mFaceData.getLeftCheekPosition();
    detectRightEarPosition = mFaceData.getRightEarPosition();
    detectLeftEarPosition = mFaceData.getLeftEarPosition();
    if ((detectPosition == null) ||
            (detectLeftEyePosition == null) ||
            (detectRightEyePosition == null) ||
            (detectNoseBasePosition == null) ||
            (detectMouthLeftPosition == null) ||
            (detectMouthBottomPosition == null) ||
            (detectMouthRightPosition == null) ||
            (detectLeftCheekPosition == null) ||
            (detectRightCheekPosition == null) ||
            (detectRightEarPosition == null) ||
            (detectLeftEarPosition == null)) {
      return;
    }
    width = scaleX(mFaceData.getWidth());
    height = scaleY(mFaceData.getHeight());
    // Eye coordinates
    PointF leftEyePosition = new PointF(translateX(detectLeftEyePosition.x),
            translateY(detectLeftEyePosition.y));
    PointF rightEyePosition = new PointF(translateX(detectRightEyePosition.x),
            translateY(detectRightEyePosition.y));
    // Nose coordinates
    PointF noseBasePosition = new PointF(translateX(detectNoseBasePosition.x),
            translateY(detectNoseBasePosition.y));
    String msg="Current angle Y : "+eulerY;
    String msg1="Current angle Z : "+eulerZ;
    String msg2="Please tilt your head to view the output";
    if(flag==1){
        drawGlasses(canvas, noseBasePosition, leftEyePosition, rightEyePosition);
    }
    else
        canvas.drawText(msg2, 75, 1000, mHintTextPaint);
  }
    private void drawGlasses(Canvas canvas,PointF noseBasePosition, PointF leftEyePosition,
                             PointF rightEyePosition) {
//    For glasses
    width = scaleX(mFaceData.getWidth());
    height = scaleY(mFaceData.getHeight());
//    int left=(int)noseBasePosition.x-(int)(width/2);
//    int right=(int)noseBasePosition.x+(int)(width/2);
    int top = ((int)leftEyePosition.y+(int)rightEyePosition.y)/2-(int)(1*height/6);
    int bottom = ((int)leftEyePosition.y+(int)rightEyePosition.y)/2+(int)(1*height/6);
      float centerX = translateX(face.getPosition().x + face.getWidth() / 2.0f);
      float centerY = translateY(face.getPosition().y + face.getHeight() / 2.0f);
      float offsetX = scaleX(face.getWidth() / 2.0f);
      float offsetY = scaleY(face.getHeight() / 2.0f);
      // 4
      // Draw a box around the face.
      int left =(int)( centerX - offsetX );
      int right =(int)( centerX + offsetX );
      float btop = (int )( centerY - offsetY );
      float bbottom =(int)( centerY + offsetY );
      canvas.drawRect(left+30,btop-30,right+30,bbottom+30, mHintOutlinePaint);
      canvas.drawText("L = "+left+" T = "+top+" R = "+right+" B"+bottom+" W = "+width+" H = "+height, 75, 1050, mHintTextPaint);
//        int left = ((int)noseBasePosition.x*3-((int)leftEyePosition.x)*2)+75;
//        int right = ((int)noseBasePosition.x*3-((int)rightEyePosition.x)*2)+75;
//        int top = ((int)leftEyePosition.y+(int)rightEyePosition.y)/2-140;
//        int bottom = ((int)leftEyePosition.y+(int)rightEyePosition.y)/2+140;

//        int left=(int)noseBasePosition.x-(int)(width/2);
//        int right=(int)noseBasePosition.x+(int)(width/2);
//        int top = (int)noseBasePosition.x-(int)(1*height/2);
//        int bottom = (int)noseBasePosition.x+(int)(1*height/2);
      String msg="Current angle Y : "+eulerY;
      String msg1="Current angle Z : "+eulerZ;
      canvas.drawText(msg, 75, 900, mHintTextPaint);
      canvas.drawText(msg1, 75, 950, mHintTextPaint);
      mGlassesGraphic.setBounds(left, top, right, bottom);
      mGlassesGraphic.draw(canvas);
    }
}