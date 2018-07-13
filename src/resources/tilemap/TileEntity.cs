using UnityEngine;
using UnityEngine.Tilemaps;

[System.Serializable]
public class TileEntity {

	[HideInInspector]
	public string name;
	public Tile tile;
	public Color color;
	public Vector2 offset;

	public TileEntity(string name, Color color) {
        this.name = name;
        this.color = color;
		this.offset = Vector2.zero;
    }

	public TileEntity(string name, Color color, Vector2 offset) {
        this.name = name;
        this.color = color;
		this.offset = offset;
    }

	public override string ToString() {
        return name;
    }

}
