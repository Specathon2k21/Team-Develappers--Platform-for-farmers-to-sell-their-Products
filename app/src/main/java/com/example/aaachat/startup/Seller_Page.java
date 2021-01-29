package com.example.aaachat.startup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaachat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Seller_Page extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private ProgressBar progressBar;
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference= FirebaseStorage.getInstance().getReference();
    private Uri imageuri;

    private ListView listView;
    private AutoCompleteTextView textView;
    private ImageButton imageButton;
    private TextView category_name;
    private DatabaseReference ref1=FirebaseDatabase.getInstance().getReference("Items");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__page);

        imageView=(ImageView)findViewById(R.id.image);
        button=(Button)findViewById(R.id.button);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,2);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageuri!=null)
                {
                    uploadimage(imageuri);
                }else{
                    Toast.makeText(Seller_Page.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });



        listView=(ListView)findViewById(R.id.listview);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        category_name=(TextView)findViewById(R.id.category_name);
        textView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        ValueEventListener event=new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  search(snapshot);
              }
            @Override
              public void onCancelled(@NonNull DatabaseError error) {
                  Toast.makeText(Seller_Page.this, "", Toast.LENGTH_SHORT).show();
              }
          };
        ref1.addListenerForSingleValueEvent(event);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode==RESULT_OK && data!=null){
            imageuri=data.getData();
            imageView.setImageURI(imageuri);
        }
    }
    private void uploadimage(Uri uri) {

        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Model model=new Model(uri.toString());
                        String modelId=ref.push().getKey();
                        ref.child(modelId).setValue(model);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Seller_Page.this, "upload succesfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Seller_Page.this, "uploading failue", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String getFileExtension(Uri muri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri));
    }



    private void search(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists()) {
            for (DataSnapshot ds : snapshot.getChildren()) {
                for(DataSnapshot ds1:ds.getChildren())
                {
                    String name=ds1.getValue(String.class);
                    names.add(name);
                }

            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
            textView.setAdapter(adapter);
            category_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref1=FirebaseDatabase.getInstance().getReference("items");
                    ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            for(DataSnapshot Parent:datasnapshot.getChildren())
                            {
                                for(DataSnapshot child:Parent.getChildren())
                                {
                                    String children=child.getValue(String.class);
                                    if(children.equals(textView.getText().toString().toUpperCase()))
                                    {
                                        String parent_name= Parent.getKey();
                                        category_name.setText(parent_name);
                                        break;
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            });
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu=new PopupMenu(Seller_Page.this,v);
                    popupMenu.getMenuInflater().inflate(R.menu.category,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.getItemId()==R.id.item1)
                            {
                                category_name.setText(item.getTitle());
                            }
                            if(item.getItemId()==R.id.item2)
                            {
                                category_name.setText(item.getTitle());
                            }
                            if(item.getItemId()==R.id.item3)
                            {
                                category_name.setText(item.getTitle());
                            }
                            if(item.getItemId()==R.id.item4)
                            {
                                category_name.setText(item.getTitle());
                            }
                            if(item.getItemId()==R.id.item5)
                            {
                                category_name.setText(item.getTitle());
                            }
                            if(item.getItemId()==R.id.item6)
                            {
                                category_name.setText(item.getTitle());
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
        }
    }
}