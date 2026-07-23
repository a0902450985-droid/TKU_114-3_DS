public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        this.head = null;
    }

    // 功能要求：依代碼搜尋歌曲
    public PlaylistNode searchByCode(String songCode) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songCode.equalsIgnoreCase(songCode)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // 功能要求：尾端新增（歌曲代碼不可重複）
    public boolean addLast(String songCode, String songName) {
        // 檢查代碼是否重複
        if (searchByCode(songCode) != null) {
            System.out.println("[新增失敗] 歌曲代碼已存在: " + songCode);
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songCode, songName);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("[新增成功] [" + songCode + "] " + songName);
        return true;
    }

    // 功能要求：依代碼刪除（正確處理 Head、Middle、Tail）
    public boolean removeByCode(String songCode) {
        if (head == null) {
            System.out.println("[刪除失敗] 播放清單為空，無法刪除: " + songCode);
            return false;
        }

        // 情況 A：刪除第一首歌曲 (Head)
        if (head.songCode.equalsIgnoreCase(songCode)) {
            System.out.println("[刪除成功] 已移除第一首歌曲: [" + head.songCode + "] " + head.songName);
            head = head.next;
            return true;
        }

        // 情況 B & C：刪除中間或最後一首歌曲 (Tail)
        PlaylistNode current = head;
        while (current.next != null && !current.next.songCode.equalsIgnoreCase(songCode)) {
            current = current.next;
        }

        // 找不到資料
        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到歌曲代碼: " + songCode);
            return false;
        }

        System.out.println("[刪除成功] 已移除歌曲: [" + current.next.songCode + "] " + current.next.songName);
        current.next = current.next.next;
        return true;
    }

    // 功能要求：印出完整播放順序
    public void printPlaylist() {
        if (head == null) {
            System.out.println("【播放清單】: [ 空白清單 ]");
            return;
        }

        System.out.println("--- 完整播放順序 ---");
        PlaylistNode current = head;
        int track = 1;
        while (current != null) {
            System.out.println("  軌道 " + track + ": [" + current.songCode + "] " + current.songName);
            current = current.next;
            track++;
        }
        System.out.println("--------------------");
    }
}
