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

package impl

import android.content.Context
import android.view.ViewGroup
import base.AflFacing
import base.CameraFacingImpl
import com.google.android.cameraview.CameraView

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

abstract class AflCameraImpl {
    var mCameraView: CameraView

    lateinit var aflCameraFacingImpl: CameraFacingImpl

    var aflCameraId: Int = -1

    constructor(ctx: Context) {
        mCameraView = CameraView(ctx)
    }

    fun start() = {
        mCameraView.start()
    }

    fun setCameraFaceImpl(aflCameraImpl: CameraFacingImpl) {
        this.aflCameraFacingImpl = aflCameraImpl
        aflCameraId = aflCameraFacingImpl.getCameraId(getAflCameraFacing())
        mCameraView.setCameraId(aflCameraId)
    }

    fun stop() = mCameraView.stop()


    protected fun isCameraOpen() = mCameraView.isCameraOpened

    abstract fun getAflCameraFacing(): AflFacing

    protected fun setCallback(callback: CameraView.Callback) = mCameraView.addCallback(callback)

    fun setParentView(viewGroup: ViewGroup) {
        viewGroup.addView(mCameraView)
    }

}