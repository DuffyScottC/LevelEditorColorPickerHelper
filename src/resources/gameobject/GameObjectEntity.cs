using UnityEngine;

[System.Serializable]
public class GameObjectEntity {
    
    [HideInInspector]
    public string name;
    [HideInInspector]
    public GameObject gameObject;
    public Color32 color;
    [HideInInspector]
    public Vector2 offset;

    public GameObjectEntity(string name, Color32 color) {
        this.name = name;
        this.color = color;
        this.offset = Vector2.zero;
    }

    public GameObjectEntity(string name, Color32 color, Vector2 offset) {
        this.name = name;
        this.color = color;
        this.offset = offset;
    }

    public override string ToString() {
        return name;
    }

}
