package com.example.basicprogramming.dbflowappdemo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.basicprogramming.dbflowappdemo.R;
import com.example.basicprogramming.dbflowappdemo.entities.Book;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> bookArrayList;
    private TextView nameTextView;
    private Dialog dialog;

    public BookAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
        this.dialog = new Dialog(context, R.style.appDialog);
    }

    @Override
    public int getCount() {
        return bookArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.book_list_item, null);
        }

        nameTextView = convertView.findViewById(R.id.book_name_text_view);
        TextView authorTextView = convertView.findViewById(R.id.author_name_text_view);
        TextView priceTextView = convertView.findViewById(R.id.book_price_text_view);

        Book book = bookArrayList.get(position);

        nameTextView.setText(book.getBookName());
        authorTextView.setText(book.getAuthorName());
        priceTextView.setText("Price ($" + book.getBookPrice() + ")");
        setListener(position);
        return convertView;
    }

    public void setListener(final int position) {


        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setTitle("Book Detail");
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.detail_dialog_items);

                TextView tv1 = dialog.findViewById(R.id.detail_book_name_text_view);
                TextView tv2 = dialog.findViewById(R.id.detail_author_name_text_view);
                TextView tv3 = dialog.findViewById(R.id.detail_book_price_text_view);

                // get book by position

                Book book = bookArrayList.get(position);

                tv1.setText("Book Name\n" + book.getBookName());
                tv2.setText("Author Name\n" + book.getAuthorName());
                tv3.setText("Price\n$" + book.getBookPrice());
                dialog.show();

            }
        });

    }
}
