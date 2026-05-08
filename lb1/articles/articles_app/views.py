from django.shortcuts import render
from .models import Category
from django.db.models import Count
from .models import Article
from django.shortcuts import get_object_or_404, redirect
from .models import Comment

def category_list(request):
    categories = Category.objects.all()
    return render(request, 'articles_app/category_list.html', {
        'categories': categories
    })


def article_list(request, category_id):
    sort = request.GET.get('sort', 'date')

    articles = Article.objects.filter(
        category_id=category_id
    ).annotate(
        comments_count=Count('comments')
    )

    if sort == 'comments':
        articles = articles.order_by('-comments_count')
    else:
        articles = articles.order_by('-published_at')

    return render(request, 'articles_app/article_list.html', {
        'articles': articles
    })

def article_detail(request, article_id):
    article = get_object_or_404(
        Article,
        id=article_id
    )

    if request.method == 'POST':
        author = request.POST.get('author')
        text = request.POST.get('text')

        Comment.objects.create(
            article=article,
            author=author,
            text=text
        )

        return redirect(
            'article_detail',
            article_id=article.id
        )

    return render(request, 'articles_app/article_detail.html', {
        'article': article
    })

def delete_comment(request, comment_id):
    comment = get_object_or_404(
        Comment,
        id=comment_id
    )

    article_id = comment.article.id

    comment.delete()

    return redirect(
        'article_detail',
        article_id=article_id
    )