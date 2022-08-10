package com.example.testpermissionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Alert Dialog");

        builder.setMessage("In order to make this app function properly, we need following permissions :- " + "\n"+
                "Camera - to enable Take Photo functionality" + "\n"+
                "Location - To Automatically gather current location" + "\n"+
                "Storage - to export data into CSV files and image files"
        );

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               checkMultiplePermissions();
            }
        });

        AlertDialog diag = builder.create();

        //Display the message!
        diag.show();




    }

    private void checkMultiplePermissions() {

        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionsNeeded = new ArrayList<String>();
            List<String> permissionsList = new ArrayList<String>();

            if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                permissionsNeeded.add("GPS");
            }

            if (!addPermission(permissionsList, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissionsNeeded.add("Read Storage");
            }
            if (!addPermission(permissionsList, Manifest.permission.CAMERA)) {
                permissionsNeeded.add("Camera Permission");
            }

            if (permissionsList.size() > 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                return;
            }
        }
    }



    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= 23)

            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);

                // Check for Rationale Option
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(android.Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);


                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                if (perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                ) {
                    // All Permissions Granted
                    return;
                }



                if(perms.get(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    if (perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                        if(perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Camera"+"\n" +
                                    "Location"+"\n"+
                                    "Storage"
                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();

                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Camera"+"\n" +
                                    "Location"
                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();

                        }

                    }else{
                        if(perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Camera"+"\n" +
                                    "Storage"
                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();

                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Camera"
                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();

                        }

                    }


//                    else if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Alert Dialog");
//                    builder.setMessage("Permission for the following are not granted " + "\n" +
//                            "Camera"+"\n" +
//                            "Location"
//                    );
//                    builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                        }
//                    });
//
//                    builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                            System.exit(0);
//                        }
//                    });
//
//                    builder.show();
//
//                    }


//                    else if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
//                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                        builder.setTitle("Alert Dialog");
//                        builder.setMessage("Permission for the following are not granted " + "\n" +
//                                "Camera"+"\n" +
//                                "Location" + "\n" +
//                                "Storage"
//
//
//                        );
//                        builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                            }
//                        });
//
//                        builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                finish();
//                                System.exit(0);
//                            }
//                        });
//
//                        builder.show();
//                    }
//
//                    else{
//                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                        builder.setTitle("Alert Dialog");
//                        builder.setMessage("Permission for the following are not granted " + "\n" +
//                                "Camera"
//                        );
//                        builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                            }
//                        });
//
//                        builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                finish();
//                                System.exit(0);
//                            }
//                        });
//
//                        builder.show();
//                    }
                }
                if(perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                        if(perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Location"

                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Location" + "\n" +
                                    "Storage"
                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();

                        }
                    }
                    else{

                        if(perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                            AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            builder.setTitle("Alert Dialog");
                            builder.setMessage("Permission for the following are not granted " + "\n" +
                                    "Storage"

                            );
                            builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                                }
                            });

                            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            });

                            builder.show();
                        }

                    }

                }


//                else if (perms.get(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&  perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                    // Permission Denied Location
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Alert Dialog");
//                    builder.setMessage("Permission for the following are not granted " + "\n" +
//                            "Camera"+"\n" +
//                            "Storage"
//                    );
//                    builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                        }
//                    });
//
//                    builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                            System.exit(0);
//                        }
//                    });
//
//                    builder.show();
//
//                }
//                else if (perms.get(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&  perms.get(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                    // Permission Denied Location
//
//                }
//                else if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&  perms.get(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                    // Permission Denied Location
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Alert Dialog");
//                    builder.setMessage("Permission for the following are not granted " + "\n" +
//                            "Storage"+"\n" +
//                            "Location"
//                    );
//                    builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                        }
//                    });
//
//                    builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                            System.exit(0);
//                        }
//                    });
//
//                    builder.show();
//
//                }
//                else if (perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                    // Permission Denied Location
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Alert Dialog");
//                    builder.setMessage("Permission for Location is not granted.");
//                    builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                        }
//                    });
//
//                    builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                            System.exit(0);
//                        }
//                    });
//
//                    builder.show();
//
//                }
//                else if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                    // Permission Denied Location
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Alert Dialog");
//                    builder.setMessage("Permission for Storage is not granted.");
//                    builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                        }
//                    });
//
//                    builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                            System.exit(0);
//                        }
//                    });
//
//                    builder.show();
//
//                }
//                else if (perms.get(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//                    // Permission Denied Location
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Alert Dialog");
//                    builder.setMessage("Permission for Camera is not granted.");
//                    builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
//
//                        }
//                    });
//
//                    builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                            System.exit(0);
//                        }
//                    });
//
//                    builder.show();
//
//                }
//

            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}