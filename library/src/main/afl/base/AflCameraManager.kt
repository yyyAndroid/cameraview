package base

import BackCamera
import FrontCamera
import android.content.Context
import android.hardware.Camera
import com.google.android.cameraview.CameraView
import com.google.android.cameraview.demo.DefaultBackCameraID
import com.google.android.cameraview.demo.DefaultFrontCameraID
import impl.AflCameraImpl
import kotlin.collections.HashMap

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

open class AflCameraManager private constructor() {

    private lateinit var aflCameras: HashMap<AflFacing, AflCameraImpl>

    private lateinit var context: Context

    private var mFaceing: AflFacing = AflFacing.FACING_BACK

    fun setContext(ctx: Context): AflCameraManager {
        context = ctx
        return this
    }

    fun setFacing(facing: AflFacing): AflCameraManager {
        mFaceing = facing
        return this
    }

    fun createCamera(facing: AflFacing) {
        createCamera(facing, DefaultFrontCameraID(), DefaultBackCameraID())
    }

    fun createCamera(
            facing: AflFacing,
            frontCameraFacingImpl: CameraFacingImpl,
            backFameraFacingImpl: CameraFacingImpl
    ) {

        aflCameras = HashMap()

        mFaceing = facing

        when (Camera.getNumberOfCameras()) {
            1 -> aflCameras.put(
                    AflFacing.FACING_BACK,
                    BackCamera(context, backFameraFacingImpl)
            )
            2 or 3 -> when (facing) {
                AflFacing.FACING_FONT -> aflCameras.put(
                        facing,
                        FrontCamera(context, frontCameraFacingImpl)
                )

                AflFacing.FACING_BACK -> aflCameras.put(
                        facing,
                        BackCamera(context, backFameraFacingImpl)
                )

                AflFacing.FACING_DOUBLE -> {
                    aflCameras.put(AflFacing.FACING_BACK, BackCamera(context, backFameraFacingImpl))
                    aflCameras.put(
                            AflFacing.FACING_FONT,
                            FrontCamera(context, frontCameraFacingImpl)
                    )
                }
            }
        }
    }

    /**
     * 获取前置摄像头照片
     */
    fun takeFrontPicture(takePictureCallback: CameraView.TakePictureCallback) {
        if (getAflCamera(AflFacing.FACING_FONT) == null || getAflCamera(AflFacing.FACING_FONT)?.mCameraView == null) {
            throw RuntimeException("获取FRONT相机对象为空")
        }
        getAflCamera(AflFacing.FACING_FONT)?.mCameraView?.setTakePicture(takePictureCallback)?.takePicture()
    }

    /**
     * 获取舱内照片
     */
    fun takeBackPicture(takePictureCallback: CameraView.TakePictureCallback) {
        if (getAflCamera(AflFacing.FACING_BACK) == null || getAflCamera(AflFacing.FACING_BACK)?.mCameraView == null) {
            throw RuntimeException("获取BACK相机对象为空")
        }
        getAflCamera(AflFacing.FACING_BACK)?.mCameraView?.setTakePicture(takePictureCallback)?.takePicture()
    }

    fun start() {
        aflCameras.filter {
            it.value.aflCameraId != -1
        }.forEach {
            it.value.mCameraView.start()
        }
    }

    fun stop() {
        aflCameras.filter { it.value.aflCameraId != -1 }.forEach {
            it.value.stop()
        }
    }

    fun getFacing(): AflFacing {
        return mFaceing
    }

    fun getAflCamera(facing: AflFacing): AflCameraImpl? {
        return aflCameras.get(facing)
    }

    companion object {
        val mInstant: AflCameraManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AflCameraManager()
        }
    }
}