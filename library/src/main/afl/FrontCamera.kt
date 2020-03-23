import android.content.Context
import base.AflFacing
import base.CameraFacingImpl
import com.google.android.cameraview.CameraView
import impl.AflCameraImpl

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

class FrontCamera : AflCameraImpl {
    override fun getAflCameraFacing(): AflFacing {
        return AflFacing.FACING_FONT
    }


    constructor(context: Context, cameraFacingImpl: CameraFacingImpl) : super(context) {
        setCameraFaceImpl(cameraFacingImpl)
    }
}