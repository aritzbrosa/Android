package local.dam_2015.intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static local.dam_2015.intent.R.id.TextPhoto;


public class PhotoActivity extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int DETAIL_ACTIVITY_CODE = 8;
    public static final String TEXT = "title";
    private Button btnSend;
    private Button btnPhoto;
    private ImageView photoView;
    private TextView textView;
    private EditText textEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        btnPhoto = (Button) findViewById(R.id.snap);
        btnSend = (Button) findViewById(R.id.send);
        photoView = (ImageView) findViewById(R.id.photo);
        textView = (TextView) findViewById(R.id.TextPhotoDescription);
        textEdit = (EditText) findViewById(R.id.TextPhoto);

        addEventListeners();
    }

    private void addEventListeners(){

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = textEdit.getText().toString();
                Intent detailIntent = new Intent(PhotoActivity.this , DetailActivity.class);

                if(title.length() > 0){
                    detailIntent.putExtra(TEXT, title);
                } else {
                    Toast toast = Toast.makeText(PhotoActivity.this, getResources().getString(R.string.no_text),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0 );
                    toast.show();
                }


                startActivityForResult(detailIntent, DETAIL_ACTIVITY_CODE);
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else
        {
            Toast toast = Toast.makeText(PhotoActivity.this, getResources().getString(R.string.no_photo),Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0 );
            toast.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            switch(requestCode){
                case DETAIL_ACTIVITY_CODE:
                        textView.setText(data.getStringExtra(DetailActivity.MSG));
                    break;
                case REQUEST_IMAGE_CAPTURE:
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        photoView.setImageBitmap(imageBitmap);
                    break;
                default: break;
            }
        }
    }
}
