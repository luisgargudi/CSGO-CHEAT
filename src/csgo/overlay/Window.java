package csgo.overlay;


import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

public class Window {
    private static WinDef.HWND hwnd;
    public Window(String name){
        hwnd = User32.INSTANCE.FindWindow(null, name);
    }

    public static int getWidth(){
        WinDef.RECT rect = new WinDef.RECT();
        boolean res = User32.INSTANCE.GetWindowRect(hwnd, rect);
        if(res)
            return rect.bottom - rect.top;
        return -1;
    }public static int getHeight(){
        WinDef.RECT rect = new WinDef.RECT();
        boolean res = User32.INSTANCE.GetWindowRect(hwnd, rect);
        if(res)
            return rect.bottom - rect.top;
        return -1;
    }
}
