package com.example.basicprogramming.dbflowappdemo.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.basicprogramming.dbflowappdemo.R;
import com.example.basicprogramming.dbflowappdemo.entities.Book;
import com.valdesekamdem.library.mdtoast.MDToast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText name, author, price;
    private Book book;
    private MDToast mdToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        // register view here
        name = findViewById(R.id.book_name_edit_text);
        author = findViewById(R.id.author_name_edit_text);
        price = findViewById(R.id.book_price_edit_text);
        fab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addBooks() {

        String book_name = name.getText().toString().trim();
        String author_name = author.getText().toString().trim();
        String book_price = price.getText().toString().trim();

        if (book_name.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "book name is required",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
            mdToast.show();
            return;
        }

        if (author_name.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "author name is required",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
            mdToast.show();
            return;
        }

        if (book_price.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "book price is required",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
            mdToast.show();
            return;
        }

        book = new Book();

        // saved books

        book.setBookName(book_name);
        book.setAuthorName(author_name);
        book.setBookPrice(Double.parseDouble(book_price));

        // add books
        book.save();

        //result is ok
        setResult(RESULT_OK);
        mdToast = MDToast.makeText(AddActivity.this,
                "book saved into database",
                MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
        mdToast.show();
        startActivity(new Intent(AddActivity.this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        addBooks();
    }
}
