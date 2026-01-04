// 通用工具函数
function showMessage(message, type) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    const container = document.querySelector('.main .container');
    if (container) {
        container.insertBefore(alertDiv, container.firstChild);
        setTimeout(() => alertDiv.remove(), 3000);
    }
}

// 投票功能
function vote(novelId) {
    fetch(`/novel/vote/${novelId}`, {
        method: 'POST'
    })
    .then(response => response.text())
    .then(result => {
        if (result === 'success') {
            showMessage('投票成功！', 'success');
            setTimeout(() => location.reload(), 1000);
        }
    })
    .catch(error => {
        showMessage('投票失败，请重试', 'error');
    });
}

// 收藏功能
function toggleBookmark(novelId, isBookmarked) {
    const action = isBookmarked ? 'remove' : 'add';
    fetch(`/novel/bookmark/${action}/${novelId}`, {
        method: 'POST'
    })
    .then(response => response.text())
    .then(result => {
        if (result === 'success') {
            showMessage(isBookmarked ? '已取消收藏' : '收藏成功！', 'success');
            setTimeout(() => location.reload(), 1000);
        } else if (result === 'not_login') {
            showMessage('请先登录', 'error');
            setTimeout(() => window.location.href = '/login', 1000);
        }
    })
    .catch(error => {
        showMessage('操作失败，请重试', 'error');
    });
}

// 确认删除
function confirmDelete(message, url) {
    if (confirm(message || '确定要删除吗？')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = url;
        document.body.appendChild(form);
        form.submit();
    }
}

// 页面加载完成后执行
document.addEventListener('DOMContentLoaded', function() {
    // 自动隐藏提示消息
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 300);
        }, 3000);
    });
});
