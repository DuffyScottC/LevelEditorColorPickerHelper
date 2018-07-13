using UnityEngine;

[System.Serializable]
public class Entity
{
	[HideInInspector]
	public string name;
	public GameObject prefab;
    public Color color;
	public Vector2 offset;

	public Entity(string name, Color color) {
		this.name = name;
		this.color = color;
		this.offset = Vector2.zero;
	}

	public Entity(string name, Color color, Vector2 offset) {
		this.name = name;
        this.color = color;
        this.offset = offset;
    }

	public override string ToString() {
		return name;
	}

}
