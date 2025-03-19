// Wait for DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Set current year in footer
    document.getElementById('currentYear').textContent = new Date().getFullYear();
    
    // Preloader
    const preloader = document.querySelector('.preloader');
    window.addEventListener('load', function() {
      preloader.classList.add('hidden');
      setTimeout(() => {
        preloader.style.display = 'none';
        
        // Initialize AOS
        initAOS();
        
        // Initialize split text animation
        initSplitText();
        
        // Start counter animation
        initCounters();
      }, 500);
    });
    
    // Custom cursor
    const cursor = document.querySelector('.cursor');
    const cursorFollower = document.querySelector('.cursor-follower');
    
    document.addEventListener('mousemove', function(e) {
      cursor.style.left = e.clientX + 'px';
      cursor.style.top = e.clientY + 'px';
      
      setTimeout(function() {
        cursorFollower.style.left = e.clientX + 'px';
        cursorFollower.style.top = e.clientY + 'px';
      }, 100);
    });
    
    document.addEventListener('mousedown', function() {
      cursor.style.transform = 'translate(-50%, -50%) scale(0.7)';
      cursorFollower.style.transform = 'translate(-50%, -50%) scale(0.7)';
    });
    
    document.addEventListener('mouseup', function() {
      cursor.style.transform = 'translate(-50%, -50%) scale(1)';
      cursorFollower.style.transform = 'translate(-50%, -50%) scale(1)';
    });
    
    // Add hover effect to all links and buttons
    const links = document.querySelectorAll('a, button, .search-tab, .dot');
    links.forEach(link => {
      link.addEventListener('mouseenter', function() {
        cursor.style.transform = 'translate(-50%, -50%) scale(1.5)';
        cursor.style.backgroundColor = 'transparent';
        cursor.style.border = '1px solid var(--primary-color)';
        cursorFollower.style.opacity = 0;
      });
      
      link.addEventListener('mouseleave', function() {
        cursor.style.transform = 'translate(-50%, -50%) scale(1)';
        cursor.style.backgroundColor = 'var(--primary-color)';
        cursor.style.border = 'none';
        cursorFollower.style.opacity = 1;
      });
    });
    
    // Header scroll effect
    const header = document.querySelector('.header');
    window.addEventListener('scroll', function() {
      if (window.scrollY > 50) {
        header.classList.add('scrolled');
      } else {
        header.classList.remove('scrolled');
      }
    });
    
    // Mobile menu toggle
    const menuToggle = document.querySelector('.menu-toggle');
    const mobileMenu = document.querySelector('.mobile-menu');
    
    menuToggle.addEventListener('click', function() {
      menuToggle.classList.toggle('active');
      mobileMenu.classList.toggle('active');
      
      if (menuToggle.classList.contains('active')) {
        menuToggle.querySelector('span:first-child').style.transform = 'translateY(9px) rotate(45deg)';
        menuToggle.querySelector('span:nth-child(2)').style.opacity = '0';
        menuToggle.querySelector('span:last-child').style.transform = 'translateY(-9px) rotate(-45deg)';
      } else {
        menuToggle.querySelector('span:first-child').style.transform = 'none';
        menuToggle.querySelector('span:nth-child(2)').style.opacity = '1';
        menuToggle.querySelector('span:last-child').style.transform = 'none';
      }
    });
    
    // Close mobile menu when clicking on a link
    const mobileLinks = document.querySelectorAll('.mobile-nav-link');
    mobileLinks.forEach(link => {
      link.addEventListener('click', function() {
        menuToggle.classList.remove('active');
        mobileMenu.classList.remove('active');
        menuToggle.querySelector('span:first-child').style.transform = 'none';
        menuToggle.querySelector('span:nth-child(2)').style.opacity = '1';
        menuToggle.querySelector('span:last-child').style.transform = 'none';
      });
    });
    
    // Search tabs
    const searchTabs = document.querySelectorAll('.search-tab');
    const searchContents = document.querySelectorAll('.search-content');
    
    searchTabs.forEach(tab => {
      tab.addEventListener('click', function() {
        const target = this.getAttribute('data-tab');
        
        // Remove active class from all tabs and contents
        searchTabs.forEach(tab => tab.classList.remove('active'));
        searchContents.forEach(content => content.classList.remove('active'));
        
        // Add active class to current tab and content
        this.classList.add('active');
        document.getElementById(`${target}-tab`).classList.add('active');
      });
    });
    
    // Testimonial slider
    const testimonialSlides = document.querySelectorAll('.testimonial-slide');
    const testimonialDots = document.querySelectorAll('.dot');
    const testimonialPrev = document.querySelector('.testimonial-prev');
    const testimonialNext = document.querySelector('.testimonial-next');
    
    let currentSlide = 0;
    
    function showSlide(index) {
      // Remove active class from all slides and dots
      testimonialSlides.forEach(slide => slide.classList.remove('active'));
      testimonialDots.forEach(dot => dot.classList.remove('active'));
      
      // Add active class to current slide and dot
      testimonialSlides[index].classList.add('active');
      testimonialDots[index].classList.add('active');
      
      currentSlide = index;
    }
    
    testimonialNext.addEventListener('click', function() {
      currentSlide = (currentSlide + 1) % testimonialSlides.length;
      showSlide(currentSlide);
    });
    
    testimonialPrev.addEventListener('click', function() {
      currentSlide = (currentSlide - 1 + testimonialSlides.length) % testimonialSlides.length;
      showSlide(currentSlide);
    });
    
    testimonialDots.forEach((dot, index) => {
      dot.addEventListener('click', function() {
        showSlide(index);
      });
    });
    
    // Auto slide testimonials
    let testimonialInterval = setInterval(function() {
      currentSlide = (currentSlide + 1) % testimonialSlides.length;
      showSlide(currentSlide);
    }, 5000);
    
    // Pause auto slide on hover
    const testimonialSlider = document.querySelector('.testimonials-slider');
    testimonialSlider.addEventListener('mouseenter', function() {
      clearInterval(testimonialInterval);
    });
    
    testimonialSlider.addEventListener('mouseleave', function() {
      testimonialInterval = setInterval(function() {
        currentSlide = (currentSlide + 1) % testimonialSlides.length;
        showSlide(currentSlide);
      }, 5000);
    });
    
    // Back to top button
    const backToTop = document.querySelector('.back-to-top');
    
    window.addEventListener('scroll', function() {
      if (window.scrollY > 500) {
        backToTop.classList.add('active');
      } else {
        backToTop.classList.remove('active');
      }
    });
    
    backToTop.addEventListener('click', function() {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    });
    
    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
      anchor.addEventListener('click', function(e) {
        e.preventDefault();
        
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
          const headerHeight = document.querySelector('.header').offsetHeight;
          const targetPosition = target.getBoundingClientRect().top + window.pageYOffset - headerHeight;
          
          window.scrollTo({
            top: targetPosition,
            behavior: 'smooth'
          });
        }
      });
    });
    
    // Form validation
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
      contactForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Simple validation
        let valid = true;
        const name = document.getElementById('name');
        const email = document.getElementById('email');
        const message = document.getElementById('message');
        
        if (!name.value.trim()) {
          valid = false;
          name.style.borderColor = 'red';
        } else {
          name.style.borderColor = 'var(--border-color)';
        }
        
        if (!email.value.trim() || !isValidEmail(email.value)) {
          valid = false;
          email.style.borderColor = 'red';
        } else {
          email.style.borderColor = 'var(--border-color)';
        }
        
        if (!message.value.trim()) {
          valid = false;
          message.style.borderColor = 'red';
        } else {
          message.style.borderColor = 'var(--border-color)';
        }
        
        if (valid) {
          // Simulate form submission
          const submitBtn = contactForm.querySelector('button[type="submit"]');
          const originalText = submitBtn.textContent;
          
          submitBtn.disabled = true;
          submitBtn.textContent = 'Sending...';
          
          setTimeout(() => {
            contactForm.reset();
            submitBtn.textContent = 'Message Sent!';
            
            setTimeout(() => {
              submitBtn.disabled = false;
              submitBtn.textContent = originalText;
            }, 2000);
          }, 1500);
        }
      });
    }
    
    function isValidEmail(email) {
      const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(email).toLowerCase());
    }
    
    // Initialize AOS (Animate on Scroll)
    function initAOS() {
      const animatedElements = document.querySelectorAll('[data-aos]');
      
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.classList.add('aos-animate');
          } else {
            // Uncomment to enable re-animation when scrolling back up
            // entry.target.classList.remove('aos-animate');
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
    
    // Counter animation
    function initCounters() {
      const counters = document.querySelectorAll('.stat-number');
      
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            const target = entry.target;
            const countTo = parseInt(target.getAttribute('data-count'));
            let count = 0;
            const speed = 2000 / countTo;
            
            const updateCount = () => {
              count++;
              target.textContent = count;
              
              if (count < countTo) {
                setTimeout(updateCount, speed);
              }
            };
            
            updateCount();
            observer.unobserve(target);
          }
        });
      }, {
        threshold: 0.5
      });
      
      counters.forEach(counter => {
        observer.observe(counter);
      });
    }
    
    // Gallery lightbox
    const galleryItems = document.querySelectorAll('.gallery-item');
    
    galleryItems.forEach(item => {
      item.addEventListener('click', function() {
        const imgSrc = this.querySelector('img').getAttribute('src');
        
        // Create lightbox elements
        const lightbox = document.createElement('div');
        lightbox.className = 'lightbox';
        
        const lightboxContent = document.createElement('div');
        lightboxContent.className = 'lightbox-content';
        
        const lightboxImg = document.createElement('img');
        lightboxImg.src = imgSrc;
        
        const closeBtn = document.createElement('button');
        closeBtn.className = 'lightbox-close';
        closeBtn.innerHTML = '&times;';
        
        // Append elements
        lightboxContent.appendChild(lightboxImg);
        lightboxContent.appendChild(closeBtn);
        lightbox.appendChild(lightboxContent);
        document.body.appendChild(lightbox);
        
        // Prevent scrolling
        document.body.style.overflow = 'hidden';
        
        // Add animation
        setTimeout(() => {
          lightbox.style.opacity = 1;
          lightboxContent.style.transform = 'scale(1)';
        }, 10);
        
        // Close lightbox
        closeBtn.addEventListener('click', closeLightbox);
        lightbox.addEventListener('click', function(e) {
          if (e.target === lightbox) {
            closeLightbox();
          }
        });
        
        function closeLightbox() {
          lightbox.style.opacity = 0;
          lightboxContent.style.transform = 'scale(0.9)';
          
          setTimeout(() => {
            document.body.removeChild(lightbox);
            document.body.style.overflow = '';
          }, 300);
        }
      });
    });
    
    // Add lightbox styles
    const style = document.createElement('style');
    style.textContent = `
      .lightbox {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.9);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 9999;
        opacity: 0;
        transition: opacity 0.3s ease;
      }
      
      .lightbox-content {
        position: relative;
        max-width: 90%;
        max-height: 90%;
        transform: scale(0.9);
        transition: transform 0.3s ease;
      }
      
      .lightbox-content img {
        max-width: 100%;
        max-height: 90vh;
        display: block;
        border: 5px solid white;
        box-shadow: 0 5px 30px rgba(0, 0, 0, 0.3);
      }
      
      .lightbox-close {
        position: absolute;
        top: -40px;
        right: 0;
        width: 30px;
        height: 30px;
        background: transparent;
        border: none;
        color: white;
        font-size: 24px;
        cursor: pointer;
      }
    `;
    document.head.appendChild(style);
  });