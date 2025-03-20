// Universities Page JavaScript
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
    } else {
      htmlElement.classList.add('light-mode');
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
    
    // Mobile Menu Toggle
    const mobileMenuToggle = document.querySelector('.mobile-menu-toggle');
    const mainNav = document.querySelector('.main-nav');
    
    if (mobileMenuToggle && mainNav) {
      mobileMenuToggle.addEventListener('click', function() {
        this.classList.toggle('active');
        mainNav.classList.toggle('active');
        
        if (this.classList.contains('active')) {
          document.body.style.overflow = 'hidden';
        } else {
          document.body.style.overflow = '';
        }
      });
    }
    
    // Back to Top Button
    const backToTopButton = document.getElementById('back-to-top');
    
    if (backToTopButton) {
      window.addEventListener('scroll', function() {
        if (window.pageYOffset > 300) {
          backToTopButton.classList.add('visible');
        } else {
          backToTopButton.classList.remove('visible');
        }
      });
      
      backToTopButton.addEventListener('click', function() {
        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });
      });
    }
    
    // Featured Universities Slider
    initFeaturedSlider();
    
    // Filter Functionality
    initFilters();
    
    // Initialize AOS (Animate on Scroll)
    initAOS();
    
    // Featured Universities Slider
    function initFeaturedSlider() {
      const slider = document.querySelector('.featured-slider');
      const dots = document.querySelectorAll('.slider-dots .dot');
      const prevButton = document.querySelector('.slider-arrow.prev');
      const nextButton = document.querySelector('.slider-arrow.next');
      
      if (!slider || !dots.length || !prevButton || !nextButton) return;
      
      let currentSlide = 0;
      const totalSlides = dots.length;
      
      // Set up click events for dots
      dots.forEach((dot, index) => {
        dot.addEventListener('click', () => {
          goToSlide(index);
        });
      });
      
      // Set up click events for arrows
      prevButton.addEventListener('click', () => {
        goToSlide((currentSlide - 1 + totalSlides) % totalSlides);
      });
      
      nextButton.addEventListener('click', () => {
        goToSlide((currentSlide + 1) % totalSlides);
      });
      
      // Function to change slide
      function goToSlide(slideIndex) {
        // Update current slide
        currentSlide = slideIndex;
        
        // Update dots
        dots.forEach((dot, index) => {
          if (index === currentSlide) {
            dot.classList.add('active');
          } else {
            dot.classList.remove('active');
          }
        });
        
        // For a real slider, you would update the slider position here
        // This is a simplified example
        console.log(`Showing slide ${currentSlide + 1} of ${totalSlides}`);
      }
      
      // Auto-advance slides every 5 seconds
      let slideInterval = setInterval(() => {
        goToSlide((currentSlide + 1) % totalSlides);
      }, 5000);
      
      // Pause auto-advance on hover
      slider.addEventListener('mouseenter', () => {
        clearInterval(slideInterval);
      });
      
      // Resume auto-advance when mouse leaves
      slider.addEventListener('mouseleave', () => {
        slideInterval = setInterval(() => {
          goToSlide((currentSlide + 1) % totalSlides);
        }, 5000);
      });
    }
    
    // Filter Functionality
    function initFilters() {
      const filterTags = document.querySelectorAll('.filter-tag');
      const removeFilterButtons = document.querySelectorAll('.remove-filter');
      const clearFiltersButton = document.querySelector('.clear-filters');
      const filterSelects = document.querySelectorAll('.filter-group select');
      const filterButton = document.querySelector('.btn-filter');
      
      // Remove individual filter tags
      removeFilterButtons.forEach(button => {
        button.addEventListener('click', function() {
          const filterTag = this.parentElement;
          filterTag.remove();
          
          // If this was the last filter tag, hide the active filters section
          if (document.querySelectorAll('.filter-tag').length === 0) {
            document.querySelector('.active-filters').style.display = 'none';
          }
          
          // Apply filters (in a real app, this would filter the universities)
          applyFilters();
        });
      });
      
      // Clear all filters
      if (clearFiltersButton) {
        clearFiltersButton.addEventListener('click', function() {
          filterTags.forEach(tag => {
            tag.remove();
          });
          
          // Reset all select elements
          filterSelects.forEach(select => {
            select.selectedIndex = 0;
          });
          
          // Hide the active filters section
          document.querySelector('.active-filters').style.display = 'none';
          
          // Apply filters (in a real app, this would reset the universities list)
          applyFilters();
        });
      }
      
      // Apply filters when the filter button is clicked
      if (filterButton) {
        filterButton.addEventListener('click', function() {
          // In a real app, this would collect all filter values and apply them
          // For demo purposes, we'll just log the selected values
          const selectedFilters = {};
          
          filterSelects.forEach(select => {
            if (select.value) {
              selectedFilters[select.id] = select.value;
              console.log(`Filter applied: ${select.id} = ${select.value}`);
            }
          });
          
          // Apply filters
          applyFilters();
        });
      }
      
      // Function to apply filters (simplified for demo)
      function applyFilters() {
        // In a real app, this would filter the universities based on selected criteria
        console.log('Filters applied');
      }
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
    
    // University Search Functionality
    const searchInput = document.getElementById('university-search');
    const searchButton = document.querySelector('.search-btn');
    
    if (searchInput && searchButton) {
      searchButton.addEventListener('click', function() {
        performSearch(searchInput.value);
      });
      
      searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
          performSearch(this.value);
        }
      });
    }
    
    function performSearch(query) {
      if (!query.trim()) return;
      
      // In a real app, this would search the universities database
      console.log(`Searching for: ${query}`);
      
      // For demo purposes, show a message
      alert(`Searching for universities matching: "${query}"`);
    }
    
    // Pagination Functionality
    const paginationLinks = document.querySelectorAll('.pagination-numbers a');
    const paginationPrev = document.querySelector('.pagination-arrow.prev');
    const paginationNext = document.querySelector('.pagination-arrow.next');
    
    if (paginationLinks.length) {
      let currentPage = 1;
      
      paginationLinks.forEach(link => {
        link.addEventListener('click', function(e) {
          e.preventDefault();
          
          // Remove active class from all links
          paginationLinks.forEach(l => l.classList.remove('active'));
          
          // Add active class to clicked link
          this.classList.add('active');
          
          // Get page number
          currentPage = parseInt(this.textContent);
          
          // In a real app, this would load the corresponding page of universities
          console.log(`Loading page ${currentPage}`);
          
          // Scroll to top of universities section
          document.querySelector('.universities-grid').scrollIntoView({
            behavior: 'smooth'
          });
        });
      });
      
      if (paginationPrev) {
        paginationPrev.addEventListener('click', function(e) {
          e.preventDefault();
          
          if (currentPage > 1) {
            currentPage--;
            updatePagination();
          }
        });
      }
      
      if (paginationNext) {
        paginationNext.addEventListener('click', function(e) {
          e.preventDefault();
          
          if (currentPage < paginationLinks.length) {
            currentPage++;
            updatePagination();
          }
        });
      }
      
      function updatePagination() {
        // Remove active class from all links
        paginationLinks.forEach(link => {
          link.classList.remove('active');
          
          // Add active class to current page link
          if (parseInt(link.textContent) === currentPage) {
            link.classList.add('active');
          }
        });
        
        // In a real app, this would load the corresponding page of universities
        console.log(`Loading page ${currentPage}`);
        
        // Scroll to top of universities section
        document.querySelector('.universities-grid').scrollIntoView({
          behavior: 'smooth'
        });
      }
    }
  });