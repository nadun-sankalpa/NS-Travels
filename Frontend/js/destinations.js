// Original Destinations Page JavaScript
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
  
  // Initialize AOS
  initAOS();
  
  // Initialize split text animation
  initSplitText();
  
  // Filter functionality
  const continentFilter = document.getElementById('continent-filter');
  const categoryFilter = document.getElementById('category-filter');
  const priceFilter = document.getElementById('price-filter');
  const destinationSearch = document.getElementById('destination-search');
  const searchBtn = document.querySelector('.search-btn');
  const destinationCards = document.querySelectorAll('.destination-card');
  
  // Apply filters when search button is clicked
  if (searchBtn) {
    searchBtn.addEventListener('click', function() {
      applyFilters();
    });
  }
  
  // Apply filters when Enter key is pressed in search input
  if (destinationSearch) {
    destinationSearch.addEventListener('keyup', function(event) {
      if (event.key === 'Enter') {
        applyFilters();
      }
    });
  }
  
  // Apply filters when select elements change
  if (continentFilter) {
    continentFilter.addEventListener('change', applyFilters);
  }
  
  if (categoryFilter) {
    categoryFilter.addEventListener('change', applyFilters);
  }
  
  if (priceFilter) {
    priceFilter.addEventListener('change', applyFilters);
  }
  
  // Apply filters function
  function applyFilters() {
    // In a real application, this would filter the destinations based on the selected criteria
    // For this demo, we'll just simulate a loading state and then show all destinations
    
    // Show loading state
    const searchBtnText = searchBtn.innerHTML;
    searchBtn.innerHTML = '<span>Searching...</span>';
    searchBtn.disabled = true;
    
    setTimeout(() => {
      // Reset button
      searchBtn.innerHTML = searchBtnText;
      searchBtn.disabled = false;
      
      // Show success message
      alert('Filters applied successfully!');
    }, 1000);
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
  
  // Set current year in footer
  const currentYearElement = document.getElementById('currentYear');
  if (currentYearElement) {
    currentYearElement.textContent = new Date().getFullYear();
  }
});