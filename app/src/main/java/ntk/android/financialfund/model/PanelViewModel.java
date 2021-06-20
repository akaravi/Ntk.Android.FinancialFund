package ntk.android.financialfund.model;

public class PanelViewModel {
    public String Title;
    public int ImageId;
    public int badgeCount;

    public PanelViewModel setTitle(String title) {
        Title = title;
        return this;
    }

    public PanelViewModel setImageId(int imageId) {
        ImageId = imageId;
        return this;
    }

    public PanelViewModel setBadgeCount(int badgeCount) {
        this.badgeCount = badgeCount;
        return this;
    }
}
