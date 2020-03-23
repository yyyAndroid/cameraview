# Deprecated

CameraView is deprecated. No more development will be taking place.

Use [Jetpack CameraX](https://developer.android.com/jetpack/androidx/releases/camerax) instead.

# CameraView

This is not an official Google product.

CameraView aims to help Android developers easily integrate Camera features.

Requires API Level 9. The library uses Camera 1 API on API Level 9-20 and Camera2 on 21 and above.

| API Level | Camera API | Preview View |
|:---------:|------------|--------------|
| 9-13      | Camera1    | SurfaceView  |
| 14-20     | Camera1    | TextureView  |
| 21-23     | Camera2    | TextureView  |
| 24        | Camera2    | SurfaceView  |

## Features

- Camera preview by placing it in a layout XML (and calling the start method)
- Configuration by attributes
  - Aspect ratio (app:aspectRatio)
  - Auto-focus (app:autoFocus)
  - Flash (app:flash)

## Usage

```xml
<com.google.android.cameraview.CameraView
    android:id="@+id/camera"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:keepScreenOn="true"
    android:adjustViewBounds="true"
    app:autoFocus="true"
    app:aspectRatio="4:3"
    app:facing="back"
    app:flash="auto"/>
```

```java
    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }
```
## Usage Two
```
class BackCameraID : CameraFacingImpl {
    override fun getCameraId(facing: AflFacing): Int {
        return 0
    }
}



AflCameraManager.Companion.getMInstant().setContext(this).createCamera(
                AflFacing.FACING_DOUBLE,
                new FrontCameraID(), new BackCameraID());

        AflCameraImpl c = AflCameraManager.Companion.getMInstant().getAflCamera(
                AflFacing.FACING_FONT);

        mCameraView = c.getMCameraView();
        mCameraViewTwo = AflCameraManager.Companion.getMInstant().getAflCamera(
                AflFacing.FACING_BACK).getMCameraView();




                AflCameraManager.Companion.getMInstant().start();

                AflCameraManager.Companion.getMInstant().stop();

```
You can see a complete usage in the demo app.

## Contribution

See [CONTRIBUTING.md](/CONTRIBUTING.md).
