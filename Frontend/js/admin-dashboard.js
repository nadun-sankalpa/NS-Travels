// Wait for DOM to load
document.addEventListener('DOMContentLoaded', function() {
    // Show loader
    const loader = document.getElementById('loader');
    
    // Simulate loading time
    setTimeout(() => {
        loader.style.opacity = '0';
        setTimeout(() => {
            loader.style.display = 'none';
        }, 500);
        
        // Start animations after loader is gone
        startAnimations();
    }, 1500);
    
    // Initialize live clock
    updateClock();
    setInterval(updateClock, 1000);
    
    // Initialize theme toggle
    const themeToggle = document.getElementById('theme-toggle');
    themeToggle.addEventListener('click', toggleDarkMode);
    
    // Check for saved dark mode preference
    const savedMode = localStorage.getItem('darkMode');
    if (savedMode === 'dark') {
        document.body.classList.add('dark');
    }
    
    // Initialize chat functionality
    initChat();
    
    // Initialize sales chart
    initSalesChart();
    
    // Initialize particles
    initParticles();
    
    // Initialize notifications
    initNotifications();
    
    // Initialize ripple effect
    initRippleEffect();
});

// Start animations for counters and other elements
function startAnimations() {
    // Animate stat counters
    const statValues = document.querySelectorAll('.stat-value');
    statValues.forEach(stat => {
        const value = stat.getAttribute('data-value');
        animateCounter(stat, value);
    });
}

// Animate counter from 0 to target value
function animateCounter(element, targetValue) {
    const isCurrency = element.textContent.includes('$');
    const prefix = isCurrency ? '$' : '';
    let startValue = 0;
    const duration = 2000; // 2 seconds
    const startTime = performance.now();
    
    function updateCounter(currentTime) {
        const elapsedTime = currentTime - startTime;
        const progress = Math.min(elapsedTime / duration, 1);
        
        // Use easeOutQuart easing function for a nice effect
        const easeProgress = 1 - Math.pow(1 - progress, 4);
        
        const currentValue = Math.floor(easeProgress * targetValue);
        
        if (isCurrency) {
            element.textContent = prefix + currentValue.toLocaleString();
        } else {
            element.textContent = currentValue.toLocaleString();
        }
        
        if (progress < 1) {
            requestAnimationFrame(updateCounter);
        }
    }
    
    requestAnimationFrame(updateCounter);
}

// Update the live clock
function updateClock() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    
    const timeString = `${hours}:${minutes}:${seconds}`;
    document.getElementById('live-clock').textContent = timeString;
}

// Toggle dark mode
function toggleDarkMode() {
    document.body.classList.toggle('dark');
    const isDark = document.body.classList.contains('dark');
    localStorage.setItem('darkMode', isDark ? 'dark' : 'light');
    
    // Update chart colors if chart exists
    if (window.salesChart) {
        updateChartColors();
    }
    
    // Update particles
    updateParticlesColors();
}

// Initialize chat functionality
function initChat() {
    const chatButton = document.querySelector('.chat-button');
    const chatPopup = document.querySelector('.chat-popup');
    const closeChat = document.querySelector('.close-chat');
    const sendButton = document.querySelector('.send-button');
    const chatInput = document.querySelector('.chat-input');
    const chatMessages = document.querySelector('.chat-messages');
    
    // Toggle chat popup
    chatButton.addEventListener('click', () => {
        chatPopup.style.display = chatPopup.style.display === 'block' ? 'none' : 'block';
        
        // Show typing indicator and message with delay
        if (chatPopup.style.display === 'block') {
            setTimeout(() => {
                document.querySelector('.typing-indicator').style.display = 'none';
                document.querySelector('.message-content').style.opacity = '1';
            }, 1500);
        }
    });
    
    // Close chat
    closeChat.addEventListener('click', () => {
        chatPopup.style.display = 'none';
    });
    
    // Send message
    function sendMessage() {
        const message = chatInput.value.trim();
        if (message) {
            // Add user message
            const userMessageEl = document.createElement('div');
            userMessageEl.className = 'message user';
            userMessageEl.style.backgroundColor = 'var(--primary-light)';
            userMessageEl.style.color = 'white';
            userMessageEl.style.marginLeft = 'auto';
            userMessageEl.textContent = message;
            chatMessages.appendChild(userMessageEl);
            
            // Add time
            const timeEl = document.createElement('div');
            timeEl.className = 'message-time';
            timeEl.style.textAlign = 'right';
            timeEl.textContent = 'Just now';
            chatMessages.appendChild(timeEl);
            
            // Clear input
            chatInput.value = '';
            
            // Auto response after a delay
            setTimeout(() => {
                // Add typing indicator
                const responseEl = document.createElement('div');
                responseEl.className = 'message system';
                
                const typingIndicator = document.createElement('div');
                typingIndicator.className = 'typing-indicator';
                for (let i = 0; i < 3; i++) {
                    const dot = document.createElement('span');
                    typingIndicator.appendChild(dot);
                }
                responseEl.appendChild(typingIndicator);
                
                const messageContent = document.createElement('div');
                messageContent.className = 'message-content';
                messageContent.style.opacity = '0';
                messageContent.textContent = 'Thank you for your message. Our team will get back to you shortly.';
                responseEl.appendChild(messageContent);
                
                chatMessages.appendChild(responseEl);
                
                const responseTimeEl = document.createElement('div');
                responseTimeEl.className = 'message-time';
                responseTimeEl.textContent = 'Just now';
                chatMessages.appendChild(responseTimeEl);
                
                // Show message after typing animation
                setTimeout(() => {
                    typingIndicator.style.display = 'none';
                    messageContent.style.opacity = '1';
                    
                    // Scroll to bottom
                    chatMessages.scrollTop = chatMessages.scrollHeight;
                }, 1500);
                
                // Scroll to bottom
                chatMessages.scrollTop = chatMessages.scrollHeight;
            }, 1000);
            
            // Scroll to bottom
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }
    }
    
    sendButton.addEventListener('click', sendMessage);
    
    chatInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });
}

// Initialize sales chart
function initSalesChart() {
    // Check if Chart.js is available
    if (typeof Chart === 'undefined') {
        // Load Chart.js dynamically
        const script = document.createElement('script');
        script.src = 'https://cdn.jsdelivr.net/npm/chart.js';
        script.onload = createChart;
        document.head.appendChild(script);
    } else {
        createChart();
    }
}

// Create the sales chart
function createChart() {
    const ctx = document.getElementById('sales-chart').getContext('2d');
    
    // Sample data
    const labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const data2024 = [12000, 19000, 15000, 21000, 18000, 24000, 28000, 26000, 30000, 28000, 32000, 34000];
    const data2025 = [15000, 22000, 18000, 25000, 22000, 28000, 32000, 30000, 35000, 33000, 38000, 42000];
    
    // Chart colors
    const isDark = document.body.classList.contains('dark');
    const gridColor = isDark ? 'rgba(255, 255, 255, 0.1)' : 'rgba(0, 0, 0, 0.1)';
    const textColor = isDark ? '#94a3b8' : '#64748b';
    
    window.salesChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: '2025',
                    data: data2025,
                    borderColor: '#3b82f6',
                    backgroundColor: 'rgba(59, 130, 246, 0.1)',
                    tension: 0.4,
                    fill: true,
                    borderWidth: 3,
                    pointRadius: 4,
                    pointBackgroundColor: '#3b82f6'
                },
                {
                    label: '2024',
                    data: data2024,
                    borderColor: '#93c5fd',
                    backgroundColor: 'rgba(147, 197, 253, 0.1)',
                    tension: 0.4,
                    fill: true,
                    borderWidth: 3,
                    pointRadius: 4,
                    pointBackgroundColor: '#93c5fd'
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            animation: {
                duration: 2000,
                easing: 'easeOutQuart'
            },
            scales: {
                y: {
                    beginAtZero: true,
                    grid: {
                        color: gridColor
                    },
                    ticks: {
                        color: textColor,
                        callback: function(value) {
                            return '$' + value.toLocaleString();
                        }
                    }
                },
                x: {
                    grid: {
                        color: gridColor
                    },
                    ticks: {
                        color: textColor
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    backgroundColor: isDark ? '#111111' : 'white',
                    titleColor: isDark ? '#f8fafc' : '#1e293b',
                    bodyColor: isDark ? '#f8fafc' : '#1e293b',
                    borderColor: isDark ? '#222222' : '#e2e8f0',
                    borderWidth: 1,
                    callbacks: {
                        label: function(context) {
                            return context.dataset.label + ': $' + context.raw.toLocaleString();
                        }
                    }
                }
            }
        }
    });
}

// Update chart colors when theme changes
function updateChartColors() {
    if (!window.salesChart) return;
    
    const isDark = document.body.classList.contains('dark');
    const gridColor = isDark ? 'rgba(255, 255, 255, 0.1)' : 'rgba(0, 0, 0, 0.1)';
    const textColor = isDark ? '#94a3b8' : '#64748b';
    
    window.salesChart.options.scales.y.grid.color = gridColor;
    window.salesChart.options.scales.x.grid.color = gridColor;
    window.salesChart.options.scales.y.ticks.color = textColor;
    window.salesChart.options.scales.x.ticks.color = textColor;
    
    window.salesChart.options.plugins.tooltip.backgroundColor = isDark ? '#111111' : 'white';
    window.salesChart.options.plugins.tooltip.titleColor = isDark ? '#f8fafc' : '#1e293b';
    window.salesChart.options.plugins.tooltip.bodyColor = isDark ? '#f8fafc' : '#1e293b';
    window.salesChart.options.plugins.tooltip.borderColor = isDark ? '#222222' : '#e2e8f0';
    
    window.salesChart.update();
}

// Initialize particles
function initParticles() {
    const particlesContainer = document.getElementById('particles');
    const isDark = document.body.classList.contains('dark');
    
    // Create particles
    for (let i = 0; i < 50; i++) {
        const particle = document.createElement('div');
        particle.style.position = 'absolute';
        particle.style.width = Math.random() * 5 + 'px';
        particle.style.height = particle.style.width;
        particle.style.background = isDark ? 'rgba(255, 255, 255, 0.1)' : 'rgba(37, 99, 235, 0.1)';
        particle.style.borderRadius = '50%';
        particle.style.top = Math.random() * 100 + '%';
        particle.style.left = Math.random() * 100 + '%';
        particle.style.animation = `float ${Math.random() * 10 + 5}s linear infinite`;
        particle.style.animationDelay = Math.random() * 5 + 's';
        particle.style.opacity = Math.random() * 0.5 + 0.1;
        particle.style.transition = 'background 0.5s ease';
        
        particlesContainer.appendChild(particle);
    }
}

// Update particles colors
function updateParticlesColors() {
    const particles = document.querySelectorAll('#particles div');
    const isDark = document.body.classList.contains('dark');
    
    particles.forEach(particle => {
        particle.style.background = isDark ? 'rgba(255, 255, 255, 0.1)' : 'rgba(37, 99, 235, 0.1)';
    });
}

// Initialize notifications
function initNotifications() {
    const notificationBell = document.querySelector('.notification-bell');
    const notificationDropdown = document.querySelector('.notification-dropdown');
    const markAllRead = document.querySelector('.mark-all-read');
    
    notificationBell.addEventListener('click', () => {
        notificationDropdown.style.display = notificationDropdown.style.display === 'block' ? 'none' : 'block';
    });
    
    // Close dropdown when clicking outside
    document.addEventListener('click', (e) => {
        if (!notificationBell.contains(e.target) && !notificationDropdown.contains(e.target)) {
            notificationDropdown.style.display = 'none';
        }
    });
    
    // Mark all as read
    markAllRead.addEventListener('click', () => {
        const unreadItems = document.querySelectorAll('.notification-item.unread');
        unreadItems.forEach(item => {
            item.classList.remove('unread');
        });
        
        // Update badge
        const badge = document.querySelector('.notification-badge');
        badge.textContent = '0';
        badge.style.display = 'none';
    });
}

// Initialize ripple effect
function initRippleEffect() {
    const rippleButtons = document.querySelectorAll('.ripple');
    
    rippleButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            const x = e.clientX - e.target.getBoundingClientRect().left;
            const y = e.clientY - e.target.getBoundingClientRect().top;
            
            const ripple = document.createElement('span');
            ripple.style.position = 'absolute';
            ripple.style.width = '5px';
            ripple.style.height = '5px';
            ripple.style.borderRadius = '50%';
            ripple.style.backgroundColor = 'rgba(255, 255, 255, 0.7)';
            ripple.style.transform = 'scale(0)';
            ripple.style.animation = 'ripple 0.6s linear';
            ripple.style.top = y + 'px';
            ripple.style.left = x + 'px';
            
            this.appendChild(ripple);
            
            setTimeout(() => {
                ripple.remove();
            }, 600);
        });
    });
}
// Initialize Windows 11 style background
function initParticles() {
    const particlesContainer = document.getElementById('particles');
    const isDark = document.body.classList.contains('dark');
    
    // Clear any existing particles
    particlesContainer.innerHTML = '';
    
    // Create Windows 11 style circles
    const numCircles = 15; // Fewer, larger circles
    
    for (let i = 0; i < numCircles; i++) {
        const size = Math.random() * 150 + 50; // Larger circles (50-200px)
        const particle = document.createElement('div');
        particle.className = 'particle';
        
        // Random position
        const posX = Math.random() * 100;
        const posY = Math.random() * 100;
        
        particle.style.width = size + 'px';
        particle.style.height = size + 'px';
        particle.style.left = posX + '%';
        particle.style.top = posY + '%';
        
        // Random opacity for depth effect
        const baseOpacity = Math.random() * 0.4 + 0.2; // 0.2-0.6
        particle.style.opacity = baseOpacity;
        
        // Store original position for mouse interaction
        particle.dataset.originalX = posX;
        particle.dataset.originalY = posY;
        particle.dataset.depth = Math.random() * 0.3 + 0.1; // 0.1-0.4
        
        // Custom animation for each circle
        const duration = Math.random() * 20 + 20; // 20-40s
        const delay = Math.random() * 10;
        
        particle.style.animation = `floatCircle ${duration}s ease-in-out infinite`;
        particle.style.animationDelay = `${delay}s`;
        
        particlesContainer.appendChild(particle);
    }
    
    // Add mouse movement interaction
    document.addEventListener('mousemove', moveParticles);
}

// Move particles slightly based on mouse position
function moveParticles(e) {
    const particles = document.querySelectorAll('.particle');
    const mouseX = e.clientX / window.innerWidth;
    const mouseY = e.clientY / window.innerHeight;
    
    particles.forEach(particle => {
        const depth = parseFloat(particle.dataset.depth);
        const originalX = parseFloat(particle.dataset.originalX);
        const originalY = parseFloat(particle.dataset.originalY);
        
        // Calculate new position based on mouse movement and depth
        const moveX = (mouseX - 0.5) * 40 * depth;
        const moveY = (mouseY - 0.5) * 40 * depth;
        
        // Apply the transformation
        particle.style.transform = `translate(${moveX}px, ${moveY}px)`;
    });
}

// Update particles colors
function updateParticlesColors() {
    const particles = document.querySelectorAll('.particle');
    const isDark = document.body.classList.contains('dark');
    
    particles.forEach(particle => {
        if (isDark) {
            particle.style.background = 'radial-gradient(circle at center, rgba(59, 130, 246, 0.6) 0%, rgba(59, 130, 246, 0) 70%)';
        } else {
            particle.style.background = 'radial-gradient(circle at center, rgba(255, 255, 255, 0.8) 0%, rgba(255, 255, 255, 0) 70%)';
        }
    });
}

// Add some random animations to elements
document.addEventListener('mousemove', function(e) {
    const mouseX = e.clientX / window.innerWidth;
    const mouseY = e.clientY / window.innerHeight;
    
    // Subtle parallax effect on destination cards
    const cards = document.querySelectorAll('.destination-card');
    cards.forEach(card => {
        const rect = card.getBoundingClientRect();
        const cardCenterX = rect.left + rect.width / 2;
        const cardCenterY = rect.top + rect.height / 2;
        
        const distanceX = (e.clientX - cardCenterX) / 30;
        const distanceY = (e.clientY - cardCenterY) / 30;
        
        // Only apply effect if mouse is close to the card
        const distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (distance < 100) {
            card.style.transform = `perspective(1000px) rotateY(${distanceX * 0.05}deg) rotateX(${-distanceY * 0.05}deg) translateY(-5px)`;
        } else {
            card.style.transform = '';
        }
    });
});