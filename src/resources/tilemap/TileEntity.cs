using UnityEngine;
using UnityEngine.Tilemaps;

[System.Serializable]
public class TileEntity {

    [HideInInspector]
    public string name;
    [HideInInspector]
    public TileBase tile;
    public Color32 color;
    [HideInInspector]
    public Vector2 offset;

    public TileEntity(string name, Color32 color) {
        this.name = name;
        this.color = color;
        this.offset = Vector2.zero;
    }

    public TileEntity(string name, Color32 color, Vector2 offset) {
        this.name = name;
        this.color = color;
        this.offset = offset;
    }

    public override string ToString() {
        return name;
    }

}
