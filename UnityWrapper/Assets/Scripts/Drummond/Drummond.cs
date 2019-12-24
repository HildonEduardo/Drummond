using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Drummond
{
    private AndroidJavaObject _androidObject;

    private static Drummond _instance;

    public static Drummond GetInstance()
    {
        if (_instance == null)
        {
            _instance = new Drummond();
            _instance.Init();
        }
            
        
        return _instance;
    }
    public void Init()
    {
        var jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
        var jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
        
        var drummondHelper = new AndroidJavaObject("com.help.drummond.helper.DrummondHelper");
        drummondHelper.CallStatic("initFullDebugMode", jo);

        _androidObject = drummondHelper.CallStatic<AndroidJavaObject>("getDrummond");
    }

    public void SetSymbol(string symbol)
    {
        _androidObject.Call("setSymbol", symbol);
    }

    public void V(string tag, string message)
    {
        _androidObject.Call("verbose", tag, message);
    }
    
    public void V(string message)
    {
        _androidObject.Call("v",  message);
    }
    
    public void D(string tag, string message)
    {
        _androidObject.Call("debug", tag, message);
    }
    
    public void D(string message)
    {
        _androidObject.Call("d",  message);
    }
    
    public void E(string tag, string message)
    {
        _androidObject.Call("error", tag, message);
    }
    
    public void E(string message)
    {
        _androidObject.Call("e",  message);
    }
    
    public void I(string tag, string message)
    {
        _androidObject.Call("info", tag, message);
    }
    
    public void I(string message)
    {
        _androidObject.Call("i",  message);
    }
    
    public void W(string tag, string message)
    {
        _androidObject.Call("warn", tag, message);
    }
    
    public void W(string message)
    {
        _androidObject.Call("w",  message);
    }
    
}
