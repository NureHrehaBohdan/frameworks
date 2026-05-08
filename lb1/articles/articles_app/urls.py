from django.urls import path
from . import views

urlpatterns = [
    path('categories/', views.category_list, name='category_list'),
    path(
        'categories/<int:category_id>/articles/',
        views.article_list,
        name='article_list'
    ),
    path(
        'articles/<int:article_id>/',
        views.article_detail,
        name='article_detail'
    ),
    path(
        'comments/<int:comment_id>/delete/',
        views.delete_comment,
        name='delete_comment'
    ),
]