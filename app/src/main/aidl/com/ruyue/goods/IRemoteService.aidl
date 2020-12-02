// IRemoteService.aidl
package com.ruyue.goods;

import com.ruyue.goods.Fruit;

interface IRemoteService {
    void add(in Fruit fruit);
    List<Fruit> getAll();
}