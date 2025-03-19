document.addEventListener('DOMContentLoaded', function() {
    // Theme Toggle
    const themeToggle = document.getElementById('theme-toggle');
    const htmlElement = document.documentElement;

    // Check for saved theme preference or use system preference
    const savedTheme = localStorage.getItem('theme');
    const systemPrefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;

    // Set initial theme
    if (savedTheme === 'dark' || (!savedTheme && systemPrefersDark)) {
        htmlElement.classList.add('dark-mode');
        htmlElement.classList.remove('light-mode');
    } else {
        htmlElement.classList.add('light-mode');
        htmlElement.classList.remove('dark-mode');
    }

    // Toggle theme when button is clicked
    if (themeToggle) {
        themeToggle.addEventListener('click', function() {
            if (htmlElement.classList.contains('dark-mode')) {
                htmlElement.classList.remove('dark-mode');
                htmlElement.classList.add('light-mode');
                localStorage.setItem('theme', 'light');
            } else {
                htmlElement.classList.remove('light-mode');
                htmlElement.classList.add('dark-mode');
                localStorage.setItem('theme', 'dark');
            }
        });
    }

    // Initialize animations
    initAOS();
    initSplitText();

    // Simple testimonial slider
    const testimonialCards = document.querySelectorAll('.testimonial-card');
    let currentTestimonial = 0;

    // Hide all testimonials except the first one
    if (testimonialCards.length > 1) {
        for (let i = 1; i < testimonialCards.length; i++) {
            testimonialCards[i].style.display = 'none';
        }

        // Auto rotate testimonials
        setInterval(() => {
            testimonialCards[currentTestimonial].style.display = 'none';
            currentTestimonial = (currentTestimonial + 1) % testimonialCards.length;
            testimonialCards[currentTestimonial].style.display = 'block';
        }, 8000);
    }

    // Initialize AOS (Animate on Scroll)
    function initAOS() {
        const animatedElements = document.querySelectorAll('[data-aos]');

        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('aos-animate');
                }
            });
        }, {
            threshold: 0.1
        });

        animatedElements.forEach(element => {
            observer.observe(element);

            // Add delay if specified
            const delay = element.getAttribute('data-aos-delay');
            if (delay) {
                element.style.transitionDelay = `${delay}ms`;
            }
        });
    }

    // Split text animation
    function initSplitText() {
        const splitTextElements = document.querySelectorAll('.split-text');

        splitTextElements.forEach(element => {
            const text = element.textContent;
            element.textContent = '';

            for (let i = 0; i < text.length; i++) {
                const char = document.createElement('span');
                char.className = 'char';
                char.style.transitionDelay = `${i * 30}ms`;
                char.textContent = text[i] === ' ' ? '\u00A0' : text[i];
                element.appendChild(char);
            }

            // Trigger animation after a short delay
            setTimeout(() => {
                const chars = element.querySelectorAll('.char');
                chars.forEach(char => {
                    char.style.opacity = 1;
                    char.style.transform = 'translateY(0)';
                });
            }, 500);
        });
    }

    // Set current year in footer
    const currentYearElement = document.getElementById('currentYear');
    if (currentYearElement) {
        currentYearElement.textContent = new Date().getFullYear();
    }
});
