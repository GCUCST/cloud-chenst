package cn.cst.utils;

import cn.cst.entity.Room;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class RoomsManagement {
    public static Map<String, Room> roomMap = new HashMap<>();


    //创建房间
    public static Room createRoom(Room room) {
        roomMap.put(room.getId(), room);
        return room;
    }

    //删除房间
    public static Room deleteRoom(String roomId) {
        Room remove = roomMap.remove(roomId);
        return remove;
    }

    //获取房间
    public static Room getRoomById(String roomId) {
        return roomMap.get(roomId);
    }

    //房间存在否
    public static Boolean existRoomById(String roomId) {
        return roomMap.containsKey(roomId);
    }

    //所有房间
    public static List<Room> listRooms() {
        List<Room> list = new ArrayList<>();
        Set<String> keys = roomMap.keySet();
        for (String key :
                keys) {
            list.add(roomMap.get(key));
        }
        return list;
    }


}
