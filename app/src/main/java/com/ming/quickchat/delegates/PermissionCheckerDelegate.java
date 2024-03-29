package com.ming.quickchat.delegates;

/**
 * @author Hortons
 * on 2019/7/6
 */

import permissions.dispatcher.RuntimePermissions;

/**
 * 中间层
 * 基于android6.0之后的动态权限判定的Delegate
 */

//@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

//    /**
//     * 不是直接调用方法
//     */
//    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void startCamera() {
//        LatteCamera.start(this);
//    }
//
//    /**
//     * 这个是真正调用的方法
//     */
//    public void startCameraWithCheck() {
//        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
//    }
//
//    /**
//     * 扫描二维码（不直接调用）
//     */
//    @NeedsPermission(Manifest.permission.CAMERA)
//    void startScan(BaseDelegate delegate) {
//        delegate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCodes.SCAN);
//    }
//
//    public void startScanWithCheck(BaseDelegate delegate) {
//        PermissionCheckerDelegatePermissionsDispatcher.startScanWithPermissionCheck(this, delegate);
//    }

//    @OnPermissionDenied(Manifest.permission.CAMERA)
//    void onCameraDenied() {
//        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_LONG).show();
//    }
//
//    @OnNeverAskAgain(Manifest.permission.CAMERA)
//    void onCameraNever() {
//        Toast.makeText(getContext(), "永久拒绝权限", Toast.LENGTH_LONG).show();
//    }
//
//    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onCameraRationale(PermissionRequest request) {
//        showRationaleDialog(request);
//    }

//    private void showRationaleDialog(final PermissionRequest request) {
//        new AlertDialog.Builder(getContext())
//                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("权限管理")
//                .show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case RequestCodes.TAKE_PHOTO:
//                    final Uri resultUri = CameraImageBean.getInstance().getPath();
//                    UCrop.of(resultUri, resultUri)
//                            .withMaxResultSize(400, 400)
//                            .start(getContext(), this);
//                    break;
//                case RequestCodes.PICK_PHOTO:
//                    if (data != null) {
//                        final Uri pickPath = data.getData();
//                        //从相册选择后需要有个路径存放剪裁过的图片
//                        final String pickCropPath = LatteCamera.createCropFile().getPath();
//                        UCrop.of(pickPath, Uri.parse(pickCropPath))
//                                .withMaxResultSize(400, 400)
//                                .start(getContext(), this);
//                    }
//                    break;
//                case RequestCodes.CROP_PHOTO:
//                    final Uri cropUri = UCrop.getOutput(data);
//                    //拿到剪裁后的数据进行处理
//                    @SuppressWarnings("unchecked")
//                    final IGlobalCallback<Uri> callback = CallbackManager
//                            .getInstance()
//                            .getCallback(CallbackType.ON_CROP);
//                    if (callback != null) {
//                        callback.executeCallback(cropUri);
//                    }
//                    break;
//                case RequestCodes.CROP_ERROR:
//                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

//    @Override
//    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
//        super.onFragmentResult(requestCode, resultCode, data);
//        if (requestCode == RequestCodes.SCAN) {
//            if (data != null) {
//                final String qrCode = data.getString("SCAN_RESULT");
//                final IGlobalCallback<String> callback = CallbackManager
//                        .getInstance()
//                        .getCallback(CallbackType.ON_SCAN);
//                if (callback != null) {
//                    callback.executeCallback(qrCode);
//                }
//            }
//        }
//    }
}
