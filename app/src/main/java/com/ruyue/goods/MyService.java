package com.ruyue.goods;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    private List<Fruit> fruitList;
    private IRemoteService iRemoteService = new IRemoteService.Stub() {
        @Override
        public void add(Fruit fruit) throws RemoteException {
            fruitList.add(fruit);
        }

        @Override
        public List<Fruit> getAll() throws RemoteException {
            return fruitList;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        fruitList = new ArrayList<>();
        return iRemoteService.asBinder();
    }
}