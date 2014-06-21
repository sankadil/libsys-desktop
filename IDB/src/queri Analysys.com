


SELECT author,title,assno,isbn FROM books WHERE author LIKE 'martin%'  OR author LIKE '% martin%' OR author LIKE 'doil%' OR author LIKE '%  doil%'


SELECT * FROM books WHERE author      LIKE    'martin%'             OR author        LIKE '% martin%'

SELECT * FROM books WHERE field_type LIKE  ' key_word[0] %'  OR field_type   LIKE '% key_word[0]%'