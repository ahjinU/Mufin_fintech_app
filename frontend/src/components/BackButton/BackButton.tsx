import Link from 'next/link';
import { ChevronLeftIcon } from '@heroicons/react/24/solid';

interface BackButtonProps {
  label: string;
  link: string;
}

export default function BackButton({ label, link, ...props }: BackButtonProps) {
  return (
    <nav className="flex gap-[0.5rem]">
      <ChevronLeftIcon
        className="w-[2.8rem] h-[2.8rem] text-custom-purple cursor-pointer"
        {...props}
      />
      <span className="custom-bold-text text-custom-black leading-[3.1rem]">
        {label}
      </span>
    </nav>
  );
}
