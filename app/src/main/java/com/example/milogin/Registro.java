package com.example.milogin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registro extends AppCompatActivity {
    //EditText Nombre_Usuario;
    EditText Id, Nombre, Apellido, Correo;
    EditText Contraseña, Contraseña2;

    TextView Resultado;

    //String endpoint = "https://werox99.asgardius.company/clases/create.php";
    String endpoint = "https://desktop.asgardius.company/test/restful/items/create.php";
    private DatePickerDialog datePickerDialog;
    Button Nacimiento, Aceptar, Cancelar;
    //TextView txtVolver, prueba;
    private Spinner nacionalidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        getSupportActionBar().hide();

        initDatePicker();

        Resultado = (TextView) findViewById(R.id.resultado2);
        Nacimiento = findViewById(R.id.btnNacimiento);
        Nacimiento.setText(getTodayDate());
        Aceptar = (Button) findViewById(R.id.btnAceptar);
        Cancelar = (Button) findViewById(R.id.btnCancelar);
        Id =(EditText) findViewById(R.id.id);
        Nombre = (EditText) findViewById(R.id.nombre);
        Apellido =(EditText) findViewById(R.id.apellido);
        //Nombre_Usuario = (EditText) findViewById(R.id.nombre_usuario);
        Contraseña = (EditText) findViewById(R.id.contraseña);
        Contraseña2= (EditText) findViewById(R.id.contraseña2);
        Correo = (EditText) findViewById(R.id.Correo);


        String [] leNatioonalite = {"Argentino", "Boliviano", "Canadiense", "Colombiano", "Brasileño", "Chileno", "Ecuadoriano", "Mexicano", "Peruviano", "Estados Unidense", "Español" };
        nacionalidad = findViewById(R.id.Nacionalidad);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, leNatioonalite);
        nacionalidad.setAdapter(adapter);
        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registro.this, MainActivity.class);
                startActivity(i);
            }
        });
        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String Nacionalidad = nacionalidad.getSelectedItem().toString();
                String fecha_nacimiento = Nacimiento.getText().toString();
                String intento = getTodayDate();
                if (!Nombre_Usuario.getText().toString().equals("") && !Correo.getText().toString().equals("") && !Contraseña.getText().toString().equals("")
                        && !Contraseña2.getText().toString().equals("")  && !Nacionalidad.equals("")) {
                    if (Nacionalidad.equals(intento)){
                        Toast.makeText(Registro.this, "Selecciona una fecha valida", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Contraseña.getText().toString().equals(Contraseña2.getText().toString())){
                            RegisterUser regiser = new RegisterUser();
                            String nombre_usuario = Nombre_Usuario.getText().toString();
                            String correo = Correo.getText().toString();
                            String password = Contraseña.getText().toString();
                            regiser.execute(nombre_usuario, password, correo, fecha_nacimiento, Nacionalidad);
                        } else{
                            Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Registro.this, "Esta faltando un elemento :c", Toast.LENGTH_SHORT).show();
                }

            }*/
                String conuntry = nacionalidad.getSelectedItem().toString();
                String DOB = Nacimiento.getText().toString();
                String intetento = getTodayDate();
                if (!Id.getText().toString().equals("") && !Nombre.getText().toString().equals("") && !Apellido.getText().toString().equals("") && !Correo.getText().toString().equals("")
                        && !Contraseña2.getText().toString().equals("") && !Contraseña.getText().toString().equals("") && !conuntry.equals("")) {
                    if (DOB.equals(intetento)){
                        Toast.makeText(Registro.this, "Selecciona una fecha valida", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Contraseña.getText().toString().equals(Contraseña2.getText().toString())){
                            RegisterUser registro = new RegisterUser();
                            String id_user= Id.getText().toString();
                            String name = Nombre.getText().toString();
                            String lastname = Apellido.getText().toString();
                            String email = Correo.getText().toString();
                            String pass1 = Contraseña.getText().toString();
                            registro.execute(id_user, name, lastname, email, pass1, DOB,conuntry);
                        } else{
                            Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Registro.this, "Esta faltando un elemento :c", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public class RegisterUser extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
           /* String nombre_usuario = strings[0];
            String password = strings[1];
            String correo = strings[2];
            String fecha_nacimiento = strings[3];
            String Nacionalidad = strings[4];


                String postBody = "{\n" +
                        "\"Nombre_Usuario\" : \"" + nombre_usuario + "\",\n" +
                        "\"password\" : \"" + password + "\",\n" +
                        "\"Correo\" : \"" + correo + "\",\n" +
                        "\"Fecha_Nacimiento\" : \"" + fecha_nacimiento + "\",\n" +
                        "\"Nacionalidad\" : \"" + Nacionalidad + "\",\n" + "}";
*/
            String Id= strings[0];
            String Name = strings[1];
            String LastName = strings[2];
            String email = strings[3];
            String pass = strings[4];
            String cuntry = strings[5];
            String DOB = strings[6];
           // Boolean isAdmin = false;
            String postBody = "{\n" +
                    "\"id\" : \""+Id+"\",\n" +
                    "\"firstname\" : \""+Name+"\",\n" +
                    "\"lastname\" : \""+LastName+"\",\n" +
                    "\"email\" : \""+email+"\",\n" +
                    "\"password\" : \""+pass+"\",\n" +
                    "\"country\" : \""+cuntry+"\",\n" +
                    "\"bithdate\" : \""+DOB+"\",\n" +

                    "}";
           // "\"isAdmin\" : \""+isAdmin+"\"\n"+
                System.out.println(postBody);

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
                    System.out.println(response);

                    if (response.code() == 201) {
                        result = response.body().string();
                        String finalResult = result;
                        System.out.println(finalResult);
                        Registro.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(finalResult);
                                if (finalResult.equals("true")) {
                                    Toast.makeText(Registro.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                    Resultado.setText("+");
                                    Intent i = new Intent(Registro.this, MainActivity.class);
                                    startActivity(i);
                                } else {
                                    Resultado.setText("-");
                                    Toast.makeText(Registro.this, "Error en registrar el usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;


        }

    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = makeDateString(day, month, year);
                Nacimiento.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            style = android.R.style.Theme_Material_Light_Dialog;
        }


        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private String makeDateString(int day, int month, int year) {
        //return getMonthformat(month) + "-" + day + "-" + year;
        return year + "-" + getMonthformat(month) + "-" + day;
    }

    private String getMonthformat(int month) {
       String monthString;
        switch (month){
            case 1:
                monthString= "ENE";
            break;
            case 2:
                monthString= "FEB";
            break;
            case 3:
                monthString= "MAR";
            break;
            case 4:
                monthString= "ABR";
            break;
            case 5:
                monthString= "MAY";
            break;
            case 6:
                monthString= "JUN";
            break;
            case 7:
                monthString= "JUL";
            break;
            case 8:
                monthString= "AGO";
            break;
            case 9:
                monthString= "SEP";
            break;
            case 10:
                monthString= "OCT";
            break;
            case 11:
                monthString= "NOV";
            break;
            case 12:
                monthString= "DIC";
            break;
            //default should never happen
            default:
            monthString= "ENE";
            break;
        }

    //default should never happen
        return monthString;
}
    public void OpenDatePicker(View view) {
        datePickerDialog.show();
    }
}
