package com.example.wallapoop2.product;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wallapoop2.app.ListFragment;
import com.example.wallapoop2.R;

import java.util.List;


public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ProductsHolder>
{
    private List<Product> products;
    private Context ctx;
    private int resID;
    private ListFragment fragment;

    public RecyclerProductAdapter(ListFragment fragment, List<Product> products, Context ctx, int resID)
    {
        this.fragment = fragment;
        this.products = products;
        this.ctx = ctx;
        this.resID = resID;
    }


    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ProductsHolder holder = null;
        View view = null;

        try
        {
            view = ((FragmentActivity) ctx).getLayoutInflater().inflate(this.resID, parent, false);
            holder = new ProductsHolder(view);
        }
        catch (Exception ex)
        {
            Log.i("Informacion", ex.getMessage());
        }

        view.setOnLongClickListener(this.productOnLongClick);
        view.setOnClickListener(this.productOnClick);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder holder, int position)
    {
        holder.product = this.products.get(position);

        holder.tvName.setText(holder.product.getpName());
        holder.tvPrice.setText(String.valueOf(holder.product.getpPrice()));
    }

    @Override
    public int getItemCount()
    {
        return products.size();
    }

    class ProductsHolder extends RecyclerView.ViewHolder
    {
        public View v;
        public ImageView img;
        public TextView tvName, tvPrice;
        public Product product;

        public ProductsHolder(@NonNull View itemView)
        {
            super(itemView);

            this.v = itemView;

            this.img = itemView.findViewById(R.id.imageViewProductList);
            this.tvName = itemView.findViewById(R.id.productListName);
            this.tvPrice = itemView.findViewById(R.id.productListPrice);
        }
    }


    public View.OnClickListener productOnClick;
    private View.OnLongClickListener productOnLongClick;

    public void setProductOnClick(View.OnClickListener productOnClick)
    {
        this.productOnClick = productOnClick;
    }

    public void setProductOnLongClick(View.OnLongClickListener productOnLongClick)
    {
        this.productOnLongClick = productOnLongClick;
    }

    public void changeDataItem(int position, Product model)
    {
        products.set(position, model);
    }

}
