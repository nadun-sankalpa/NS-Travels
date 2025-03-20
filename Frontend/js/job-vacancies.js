document.addEventListener('DOMContentLoaded', function() {
    const themeToggle = document.getElementById('theme-toggle');
    const htmlElement = document.documentElement;
    
    const savedTheme = localStorage.getItem('theme');
    const systemPrefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;

    if (savedTheme === 'dark' || (!savedTheme && systemPrefersDark)) {
      htmlElement.classList.add('dark-mode');
    } else {
      htmlElement.classList.add('light-mode');
    }

    if (themeToggle) {
      themeToggle.addEventListener('click', function() {
        htmlElement.classList.toggle('dark-mode');
        htmlElement.classList.toggle('light-mode');
        localStorage.setItem('theme', htmlElement.classList.contains('dark-mode') ? 'dark' : 'light');
      });
    }

    const mobileMenuToggle = document.querySelector('.mobile-menu-toggle');
    const mainNav = document.querySelector('.main-nav');

    if (mobileMenuToggle && mainNav) {
      mobileMenuToggle.addEventListener('click', function() {
        this.classList.toggle('active');
        mainNav.classList.toggle('active');
        document.body.style.overflow = this.classList.contains('active') ? 'hidden' : '';
      });
    }

    const backToTopButton = document.getElementById('back-to-top');
    
    if (backToTopButton) {
      window.addEventListener('scroll', function() {
        backToTopButton.classList.toggle('visible', window.pageYOffset > 300);
      });

      backToTopButton.addEventListener('click', function() {
        window.scrollTo({ top: 0, behavior: 'smooth' });
      });
    }

    initFilters();
    initAOS();
    initSaveJobs();
    initJobAlertForm();

    function initFilters() {
      const filterTags = document.querySelectorAll('.filter-tag');
      const removeFilterButtons = document.querySelectorAll('.remove-filter');
      const clearFiltersButton = document.querySelector('.clear-filters');
      const filterSelects = document.querySelectorAll('.filter-group select');
      const filterButton = document.querySelector('.btn-filter');

      removeFilterButtons.forEach(button => {
        button.addEventListener('click', function() {
          this.parentElement.remove();
          const activeFiltersSection = document.querySelector('.active-filters');
          if (activeFiltersSection && !document.querySelector('.filter-tag')) {
            activeFiltersSection.style.display = 'none';
          }
          applyFilters();
        });
      });

      if (clearFiltersButton) {
        clearFiltersButton.addEventListener('click', function() {
          filterTags.forEach(tag => tag.remove());
          filterSelects.forEach(select => select.selectedIndex = 0);
          const activeFiltersSection = document.querySelector('.active-filters');
          if (activeFiltersSection) activeFiltersSection.style.display = 'none';
          applyFilters();
        });
      }

      if (filterButton) {
        filterButton.addEventListener('click', function() {
          const selectedFilters = {};
          filterSelects.forEach(select => {
            if (select.value) {
              selectedFilters[select.id] = select.value;
              console.log(`Filter applied: ${select.id} = ${select.value}`);
            }
          });
          applyFilters();
        });
      }

      function applyFilters() {
        console.log('Filters applied');
      }
    }

    function initAOS() {
      const animatedElements = document.querySelectorAll('[data-aos]');
      const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.classList.add('aos-animate');
          }
        });
      }, { threshold: 0.1 });

      animatedElements.forEach(element => {
        observer.observe(element);
        const delay = element.getAttribute('data-aos-delay');
        if (delay) element.style.transitionDelay = `${delay}ms`;
      });
    }

    function initSaveJobs() {
      const saveButtons = document.querySelectorAll('.btn-save');
      saveButtons.forEach(button => {
        button.addEventListener('click', function() {
          this.classList.toggle('saved');
          const buttonText = this.querySelector('span');
          buttonText.textContent = this.classList.contains('saved') ? 'Saved' : 'Save';
        });
      });
    }

    function initJobAlertForm() {
      const jobAlertForm = document.querySelector('.job-alert-form');
      if (jobAlertForm) {
        jobAlertForm.addEventListener('submit', function(e) {
          e.preventDefault();
          const email = this.querySelector('input[type="email"]').value;
          const category = this.querySelector('select').value;

          if (!email || !category) {
            alert('Please fill in all fields');
            return;
          }

          console.log(`Job alert subscription: ${email} for category ${category}`);
          alert('Thank you for subscribing to job alerts!');
          this.reset();
        });
      }
    }

    const paginationLinks = document.querySelectorAll('.pagination-numbers a');
    const paginationPrev = document.querySelector('.pagination-arrow.prev');
    const paginationNext = document.querySelector('.pagination-arrow.next');

    if (paginationLinks && paginationLinks.length) {
      let currentPage = 1;

      paginationLinks.forEach(link => {
        link.addEventListener('click', function(e) {
          e.preventDefault();
          paginationLinks.forEach(l => l.classList.remove('active'));
          this.classList.add('active');
          currentPage = parseInt(this.textContent);
          updatePagination();
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
        paginationLinks.forEach(link => {
          link.classList.toggle('active', parseInt(link.textContent) === currentPage);
        });
        console.log(`Loading page ${currentPage}`);
      }
    }
});
