package com.ruyue.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.EditText;

import com.ruyue.goods.Fruit;
import com.ruyue.goods.IRemoteService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.goods_name)
    private EditText name;
    @BindView(R.id.goods_img)
    private EditText imageLink;
    @BindView(R.id.goods_desc)
    private EditText description;
    @BindView(R.id.goods_price)
    private EditText price;

    @BindView(R.id.recyclerview)
    private RecyclerView recyclerView;

    @OnClick({R.id.refresh, R.id.add_goods})
    void btnOnClick(Button btn) {
        switch (btn.getId()) {
            case R.id.refresh:
                refreshOrderList();
                break;
            case R.id.add_goods:
                try {
                    Fruit fruit = new Fruit();
                    fruit.setImgLink(imageLink.getText().toString());
                    fruit.setName(name.getText().toString());
                    fruit.setDescription(description.getText().toString());
                    fruit.setPrice(Integer.getInteger(price.getText().toString()));
                    iRemoteService.add(fruit);
                    refreshOrderList();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    private IRemoteService iRemoteService;
    private List<Fruit> fruitList;
    private GoodsAdapter goodsAdapter;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iRemoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iRemoteService = null;
        }
    };

    private void bindService() {
        Intent intent = new Intent("com.ruyue.goods.MyService");
        intent.setPackage("com.ruyue.goods");
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        goodsAdapter = new GoodsAdapter(fruitList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(goodsAdapter);
        bindService();
    }

    private void refreshOrderList() {
        try {
            if (iRemoteService != null) {
                fruitList = iRemoteService.getAll();
            }
            if (fruitList != null) {
                goodsAdapter.setData(fruitList);
                goodsAdapter.notifyDataSetChanged();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}