package com.example.milogin;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    EditText Correo;
    EditText Contraseña;
    TextView Registro;
    TextView Resultado;
    ImageButton nortificacion;
    Button loginBtn;
    String endpoint = "https://rikardo30miinformacionpersonal.000webhostapp.com/Api-restfull/clases/Login.php";

    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
   // private DatePickerDialog datePickerDialog;
    // private Button dateButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Registro =(TextView) findViewById(R.id.registro);
        Correo = (EditText) findViewById(R.id.Correo);
        Contraseña = (EditText) findViewById(R.id.Contraseña);
        Resultado = (TextView) findViewById(R.id.resultado);
        loginBtn = (Button) findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Correo.getText().toString().equals("") && !Contraseña.getText().toString().equals("")) {
                    String Email = Correo.getText().toString();
                    String password = Contraseña.getText().toString();

                    LoginUser login = new LoginUser();
                    login.execute(Email, password);
                } else {
                    Toast.makeText(MainActivity.this, "Esta faltando un elemento :c", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
            }
        });
        nortificacion=(ImageButton) findViewById(R.id.nortificacion);
        nortificacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Canal_Nortificacion();
                Nortifiacion();
            }
    });
    }
    /*
    private class API extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... credenciales)
        {
            String Resultado = "";
            String Correo = credenciales[0];
            String Contraseña = credenciales[1];
            endpoint = credenciales[2];
            try
            {
                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");*/
                //conn.setRequestProperty("Accept", "*/*");
               /* conn.setDoOutput(true);
                String payload = "{\n   \"user\" : \""+Correo+"\",\n   \"pass\" : \""+Contraseña+"\"\n}";
                try (OutputStream os = conn.getOutputStream())
                {
                    byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)))
                {
                    StringBuilder resp = new StringBuilder();
                    String respLine = null;
                    while ((respLine = br.readLine()) != null)
                    {
                        resp.append(respLine.toString());
                    }
                    Resultado = resp.toString();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return Resultado;
        }


        @Override
        protected void onPostExecute(String respuesta)
        {
            try
            {
                JSONObject json = new JSONObject(respuesta);
                Resultado.setText(json.getString("status"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
*/


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public class LoginUser extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String Correo = strings[0];
            String Contraseña = strings[1];
            String postBody = "{\n   \"Correo\" : \""+Correo+"\",\n   \"password\" : \""+Contraseña+"\"\n}";

            RequestBody body = RequestBody.create(JSON, postBody);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json; charset=utf8")
                    .url(endpoint)
                    .post(body)
                    .build();

            Response response = null;
            String result = " ";

            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    result = response.body().string();
                    String finalResult = result;
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(finalResult);
                            if (finalResult.equals("true")){
                                Resultado.setText("Se ingreso Correctamente");
                                Toast.makeText(MainActivity.this, "Log in Exitoso", Toast.LENGTH_SHORT).show();

                            }else{
                                Resultado.setText("Incorrecto");
                                Toast.makeText(MainActivity.this, "Error en usuario o contraseña", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
/*
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());*/

    }
/*
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        String monthString;
        switch (month){
            case 1:
                monthString= "ENERO";
                break;
            case 2:
                monthString= "FEBRERO";
                break;
            case 3:
                monthString= "MARZO";
                break;
            case 4:
                monthString= "ABRIL";
                break;
            case 5:
                monthString= "MAYO";
                break;
            case 6:
                monthString= "JUNIO";
                break;
            case 7:
                monthString= "JULIO";
                break;
            case 8:
                monthString= "AGOSTO";
                break;
            case 9:
                monthString= "SEPTIEMBRE";
                break;
            case 10:
                monthString= "OCTUBRE";
                break;
            case 11:
                monthString= "NOVIEMBRE";
                break;
            case 12:
                monthString= "DICIEMBRE";
                break;
            //default should never happen
            default:
                monthString= "ENERO";
                break;
        }

        //default should never happen
        return monthString;
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
*/

//Nortificacion
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