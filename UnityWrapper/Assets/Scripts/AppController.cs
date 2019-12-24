using UnityEngine;

public class AppController : MonoBehaviour
{
    private Drummond _drummond;
    
    private void Start()
    {
        _drummond = Drummond.GetInstance();
        _drummond.SetSymbol("@@@");
    }

    public void OnClickDebug()
    {
        _drummond.V("Test from unity");
    }
}
