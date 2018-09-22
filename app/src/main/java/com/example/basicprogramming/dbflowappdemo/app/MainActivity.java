package com.example.basicprogramming.dbflowappdemo.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.basicprogramming.dbflowappdemo.R;
import com.example.basicprogramming.dbflowappdemo.adapter.BookAdapter;
import com.example.basicprogramming.dbflowappdemo.entities.Book;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
        , AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ListView listView;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookArrayList;
    private List<Book> bookList;
    private Book book;
    private Dialog dialog;
    private MDToast mdToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new Dialog(MainActivity.this, R.style.appDialog);

        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.my_book_list_view);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        fab.setOnClickListener(this);
        getBooks();
    }

    private void getBooks() {
        bookArrayList = new ArrayList<>();
        bookList = SQLite.select().from(Book.class).queryList();
        for (int i = 0; i < bookList.size(); i++) {
            book = bookList.get(i);
            bookArrayList.add(book);
        }

        bookAdapter = new BookAdapter(MainActivity.this, bookArrayList);
        listView.setAdapter(bookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                mdToast = MDToast.makeText(MainActivity.this,
                        "Setting Menu",
                        MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
                mdToast.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        book = bookArrayList.get(position);

        dialog.setTitle("Update Book");
        dialog.setContentView(R.layout.edit_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);

        final EditText nameEditText = dialog.findViewById(R.id.edit_book_name_edit_text);
        final EditText authorEditText = dialog.findViewById(R.id.edit_author_name_edit_text);
        final EditText priceEditText = dialog.findViewById(R.id.edit_book_price_edit_text);
        Button updateButton = dialog.findViewById(R.id.update_book_button);

        nameEditText.setText(book.getBookName());
        authorEditText.setText(book.getAuthorName());
        priceEditText.setText("" + book.getBookPrice());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result = false;
                String book_name = nameEditText.getText().toString().trim();
                String author_name = authorEditText.getText().toString().trim();
                Double book_price = Double.parseDouble(priceEditText.getText().toString().trim());

                book.setBookName(book_name);
                book.setAuthorName(author_name);
                book.setBookPrice(book_price);

                result = book.save();
                bookArrayList.remove(book);
                bookArrayList.add(book);

                if (result == true) {

                    mdToast = MDToast.makeText(MainActivity.this,
                            "Update Book List Success",
                            MDToast.LENGTH_LONG,
                            MDToast.TYPE_INFO);
                    mdToast.show();
                    dialog.dismiss();
                    listView.setAdapter(bookAdapter);
                    bookAdapter.notifyDataSetChanged();

                } else {

                    mdToast = MDToast.makeText(MainActivity.this,
                            "Error Update Book From List",
                            MDToast.LENGTH_LONG,
                            MDToast.TYPE_WARNING);
                    mdToast.show();
                }
            }
        });

        dialog.show();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Book book = bookArrayList.get(position);

        boolean result = book.delete();
        bookArrayList.remove(position);

        if (result == true) {

            mdToast = MDToast.makeText(MainActivity.this,
                    "Delete Book From List",
                    MDToast.LENGTH_LONG,
                    MDToast.TYPE_WARNING);
            mdToast.show();
            listView.setAdapter(bookAdapter);
            bookAdapter.notifyDataSetChanged();

        } else {

            mdToast = MDToast.makeText(MainActivity.this,
                    "Error Delete Book From List",
                    MDToast.LENGTH_LONG,
                    MDToast.TYPE_WARNING);
            mdToast.show();
        }

        return true;
    }
}
