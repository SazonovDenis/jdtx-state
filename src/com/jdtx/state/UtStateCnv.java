package com.jdtx.state;

import com.jdtx.state.impl.*;
import com.jdtx.tree.*;

import java.util.*;

public class UtStateCnv {

    private static String keyForChilds = "__childs__";

    public static Map<String, Object> stateItemToMap(ITreeNode<StateItem> node) {
        Map<String, Object> res = new HashMap<>();

        // Элемент
        if (node.getItem() != null) {
            StateItem stateItem = node.getItem();
            // Свойства
            if (stateItem.getValues().containsKey(StateItemStackNamedImpl.keyForStateItemName)) {
                res.put("name", stateItem.getValue(StateItemStackNamedImpl.keyForStateItemName));
            }
            res.put("started", stateItem.isStarted());
            res.put("duration", stateItem.getDuration());
            // Атрибуты
            for (String key : stateItem.getValues().keySet()) {
                if (!key.startsWith("__")) {
                    res.put(key, stateItem.getValue(key));
                }
            }
        }

        // Дочерние элементы
        if (node.getChildsCount() != 0) {
            List<Map<String, Object>> childs = new ArrayList<>();

            //
            for (ITreeNode<StateItem> childNode : node.getChildNodes()) {
                childs.add(stateItemToMap(childNode));
            }

            //
            res.put(keyForChilds, childs);
        }

        //
        return res;
    }

}
