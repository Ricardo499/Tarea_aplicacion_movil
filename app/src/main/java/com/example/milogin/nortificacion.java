package com.example.milogin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class nortificacion extends AppCompatActivity {
    ImageButton nortificacion;

    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        nortificacion=(ImageButton) findViewById(R.id.nortificacion);
        nortificacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Canal_Nortificacion();
                Nortifiacion();
            }
        });

    }
    private void Canal_Nortificacion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            Uri sonido_nortifiacion = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.nortificacion_robot);
            AudioAttributes audio =new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationChannel.setSound(sonido_nortifiacion, audio);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void Nortifiacion(){
        Uri sonido_nortifiacion = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.nortificacion_robot);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.robot);
        builder.setContentTitle("Bienvenidos a nuestra aplicacion");
        builder.setContentText("Puede explorar todo el contenido de nuestra aplicación");
        builder.setColor(Color.BLACK);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setSound(sonido_nortifiacion);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }
}
