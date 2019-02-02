package com.example.exoplayer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EntradaActivity extends AppCompatActivity {

    EditText medit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        getSupportActionBar().hide();

        medit_text = (EditText)findViewById(R.id.editText_url);

    }

    public void pasarActivity (View view){

        boolean valido = android.util.Patterns.WEB_URL.matcher(medit_text.getText()).matches();

        if (valido==true){
            Intent intent = new Intent(EntradaActivity.this, MainActivity.class);
            intent.putExtra("valor_edittext", medit_text.getText().toString());
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        } else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast_layout_root));

            ImageView image = (ImageView) layout.findViewById(R.id.image);
            image.setImageResource(R.mipmap.advertencia);
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Introduce una url válida");

            Toast toast = new Toast(getApplicationContext());
            //toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }

    public void salir (View view){

        AlertDialog dialog1 = new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("¿Está seguro que quiere cerrar la aplicación?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("NO",null)
                .create();
        dialog1.show();
    }

}
