package models;
import java.util.List;

public class PagedRecipes {
    private int page;
    private int rows;
    private int total;
    private List<Recipe> recipes;

    public PagedRecipes(int page, int rows, int total, List<Recipe> recipes) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.recipes = recipes;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
