package com.practice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.R;
import com.practice.Response.Book;
import com.practice.adapter.BooksAdapter;
import com.practice.retrofit.APIClient;
import com.practice.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksFragment extends Fragment implements BooksAdapter.ItemClickListener {

    private BooksAdapter booksAdapter;
    private TextView textViewTotalBook, emptyBook;
    private RecyclerView recycleView;
    private ApiInterface apiInterface;

    public BooksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books, container, false);
        textViewTotalBook = view.findViewById(R.id.total_books);
        recycleView = view.findViewById(R.id.book_recycle_view);
        emptyBook = view.findViewById(R.id.empty_books_view);
        apiInterface = APIClient.getRetrofitClient().create(ApiInterface.class);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getBooks();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getBooks() {
        Call<Book> call = apiInterface.getBook();
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.code() == 200) {
                    setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
    }

    private void setData(Book book) {
        if(book != null) {
            textViewTotalBook.setText("Total books : " + book.getTotalItems());

            recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            booksAdapter = new BooksAdapter(getContext(), book.getItems());
            booksAdapter.setClickListener(this);
            recycleView.setAdapter(booksAdapter);
        } else {
            emptyBook.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
