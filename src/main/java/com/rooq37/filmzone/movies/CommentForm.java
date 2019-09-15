package com.rooq37.filmzone.movies;

public class CommentForm {

    private int pageSize = 10;
    private int currentPage = 0;
    private CommentSortingEnum commentSortingEnum = CommentSortingEnum.DATE_DESCENDING;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public CommentSortingEnum getCommentSortingEnum() {
        return commentSortingEnum;
    }

    public void setCommentSortingEnum(CommentSortingEnum commentSortingEnum) {
        this.commentSortingEnum = commentSortingEnum;
    }
}
