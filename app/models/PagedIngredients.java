package models;
import java.util.List;

public class PagedIngredients {
    private int page;
    private int rows;
    private int total;
    private List<Ingredient> ingredients;

    public PagedIngredients(int page, int rows, int total, List<Ingredient> ingredients) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.ingredients = ingredients;
    }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getRows() { return rows; }
    public void setRows(int rows) { this.rows = rows; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
}
