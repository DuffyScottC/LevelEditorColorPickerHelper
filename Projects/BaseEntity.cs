using UnityEngine;
using UnityEngine.Tilemaps;

[System.Serializable]
public class BaseEntity {

    [HideInInspector]
    public string name;
    public Color32 color;
    [HideInInspector]
    public Vector2 offset;


    public BaseEntity(string name, Color32 color) {
        this.name = name;
        this.color = color;
        this.offset = Vector2.zero;
    }

    public BaseEntity(string name, Color32 color, Vector2 offset) {
        this.name = name;
        this.color = color;
        this.offset = offset;
    }

    public override string ToString() {
        return name;
    }

}

[System.Serializable]
public class GOEntity : BaseEntity {
    [HideInInspector]
    public GameObject gameObject;
    public GOEntity (string name, Color32 color) 
        : base(name, color) {}
    public GOEntity(string name, Color32 color, Vector2 offset) 
        : base(name, color, offset) { }
}

[System.Serializable]
public class TEntity : BaseEntity {
    [HideInInspector]
    public TileBase tile;
    public TEntity(string name, Color32 color) 
        : base(name, color) { }
    public TEntity(string name, Color32 color, Vector2 offset) 
        : base(name, color, offset) { }
}
