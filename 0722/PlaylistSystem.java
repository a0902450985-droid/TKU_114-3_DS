public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 測試 1：空白清單邊界測試 ===");
        playlist.printPlaylist();
        playlist.removeByCode("S001");

        System.out.println("\n=== 測試 2：尾端新增與重複代碼檢查 ===");
        playlist.addLast("S001", "Despacito");
        playlist.addLast("S002", "Shape of You");
        playlist.addLast("S003", "Blinding Lights");
        playlist.addLast("S004", "Stay");

        // 測試代碼重複阻止機制
        playlist.addLast("S002", "Duplicate Song Test");

        // 印出完整播放順序
        playlist.printPlaylist();

        System.out.println("\n=== 測試 3：依代碼搜尋歌曲 ===");
        PlaylistNode found = playlist.searchByCode("S003");
        if (found != null) {
            System.out.println("搜尋結果：找到 [" + found.songCode + "] " + found.songName);
        } else {
            System.out.println("搜尋結果：未找到該歌曲");
        }

        PlaylistNode notFound = playlist.searchByCode("S999");
        if (notFound == null) {
            System.out.println("搜尋結果：未找到代碼為 S999 的歌曲");
        }

        System.out.println("\n=== 測試 4：刪除第一首歌曲 (Head) ===");
        playlist.removeByCode("S001");
        playlist.printPlaylist();

        System.out.println("\n=== 測試 5：刪除最後一首歌曲 (Tail) ===");
        playlist.removeByCode("S004");
        playlist.printPlaylist();

        System.out.println("\n=== 測試 6：刪除中間歌曲與不存在的歌曲 ===");
        playlist.removeByCode("S002");
        playlist.removeByCode("S888");
        playlist.printPlaylist();
    }
}