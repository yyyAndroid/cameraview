package com.google.android.cameraview.demo
import base.AflFacing
import base.CameraFacingImpl

class BackCameraID : CameraFacingImpl {
    override fun getCameraId(facing: AflFacing): Int {
        return 0
    }
}