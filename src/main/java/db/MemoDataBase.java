package db;

import model.Memo;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoDataBase {

    private static HashMap<Integer, Memo> memoStorage = new HashMap<>();
    private static int sequence = 0;

    public static void addMemo(Memo memo){
        memo.setId(++sequence);
        memoStorage.put(memo.getId(), memo);
    }

    public static List<Memo> findMemoAll(){
        return new ArrayList<>(memoStorage.values());
    }

}
