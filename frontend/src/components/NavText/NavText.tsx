import Link from 'next/link';

interface NavTextProps {
  text: string;
  link: string;
}

export default function NavText({ text, link, ...props }: NavTextProps) {
  return (
    <nav
      className="custom-light-text underline text-custom-dark-purple cursor-pointer"
      {...props}
    >
      <Link href={link}>{text}</Link>
    </nav>
  );
}
