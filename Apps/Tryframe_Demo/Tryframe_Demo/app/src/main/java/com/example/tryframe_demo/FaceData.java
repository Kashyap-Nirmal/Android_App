package com.example.tryframe_demo;

import android.graphics.PointF;

/**
 * Created by joey on 3/11/17.
 */

public class FaceData {
  private static final String TAG = "FaceData";
  private int mId;
  // Face dimensions
  private PointF mPosition;
  private float mWidth;
  private float mHeight;
  // Head orientation
  private float mEulerY;
  private float mEulerZ;
  // Facial states
  // Facial landmarks
  private PointF mLeftEyePosition;
  private PointF mRightEyePosition;
  private PointF mLeftCheekPosition;
  private PointF mRightCheekPosition;
  private PointF mNoseBasePosition;
  private PointF mLeftEarPosition;
  private PointF mLeftEarTipPosition;
  private PointF mRightEarPosition;
  private PointF mRightEarTipPosition;
  private PointF mMouthLeftPosition;
  private PointF mMouthBottomPosition;
  private PointF mMouthRightPosition;
  public int getId() {
    return mId;
  }
  public void setId(int id) {
    mId = id;
  }
  public PointF getPosition() {
    return mPosition;
  }
  public void setPosition(PointF position) {
    mPosition = position;
  }
  public float getWidth(){
    return mWidth;
  }
  public void setWidth(float width) {
    mWidth = width;
  }
  public float getHeight() {
    return mHeight;
  }
  public void setHeight(float height) {
    mHeight = height;
  }
  public float getEulerY() {
    return mEulerY;
  }
  public void setEulerY(float eulerY) {
    mEulerY = eulerY;
  }
  public float getEulerZ() {
    return mEulerZ;
  }
  public void setEulerZ(float eulerZ) {
    mEulerZ = eulerZ;
  }
  public PointF getLeftEyePosition() {
    return mLeftEyePosition;
  }

  public void setLeftEyePosition(PointF leftEyePosition) {
    this.mLeftEyePosition = leftEyePosition;
  }
  public PointF getRightEyePosition() {
    return mRightEyePosition;
  }
  public void setRightEyePosition(PointF rightEyePosition) {
    this.mRightEyePosition = rightEyePosition;
  }
  public PointF getLeftCheekPosition() {
    return mLeftCheekPosition;
  }
  public void setLeftCheekPosition(PointF leftCheekPosition) {
    mLeftCheekPosition = leftCheekPosition;
  }
  public PointF getRightCheekPosition() {
    return mRightCheekPosition;
  }
  public void setRightCheekPosition(PointF rightCheekPosition) {
    mRightCheekPosition = rightCheekPosition;
  }
  public PointF getNoseBasePosition() {
    return mNoseBasePosition;
  }
  public void setNoseBasePosition(PointF noseBasePosition) {
    this.mNoseBasePosition = noseBasePosition;
  }
  public PointF getLeftEarPosition() {
    return mLeftEarPosition;
  }
  public void setLeftEarPosition(PointF leftEarPosition) {
    this.mLeftEarPosition = leftEarPosition;
  }
  public void setLeftEarTipPosition(PointF leftEarTipPosition) {
    this.mLeftEarTipPosition = leftEarTipPosition;
  }
  public PointF getRightEarPosition() {
    return mRightEarPosition;
  }
  public void setRightEarPosition(PointF rightEarPosition) {
    this.mRightEarPosition = rightEarPosition;
  }
  public void setRightEarTipPosition(PointF rightEarTipPosition) {
    this.mRightEarTipPosition = rightEarTipPosition;
  }
  public PointF getMouthLeftPosition() {
    return mMouthLeftPosition;
  }
  public void setMouthLeftPosition(PointF mouthLeftPosition) {
    this.mMouthLeftPosition = mouthLeftPosition;
  }
  public PointF getMouthBottomPosition() {
    return mMouthBottomPosition;
  }
  public void setMouthBottomPosition(PointF mouthBottomPosition) {
    this.mMouthBottomPosition = mouthBottomPosition;
  }
  public PointF getMouthRightPosition() {
    return mMouthRightPosition;
  }
  public void setMouthRightPosition(PointF mouthRightPosition) {
    this.mMouthRightPosition = mouthRightPosition;
  }
}