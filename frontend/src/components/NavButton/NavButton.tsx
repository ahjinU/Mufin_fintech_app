'use client';

import Link from 'next/link';
import { ChevronRightIcon } from '@heroicons/react/24/solid';

interface NavButtonProps {
  mode: 'HIGHLIGHT' | 'GENERAL';
  label: string;
  link: string;
}

export default function NavButton({
  mode,
  label,
  link,
  ...props
}: NavButtonProps) {
  const textStyle =
    mode === 'HIGHLIGHT' ? 'custom-bold-text' : 'custom-medium-text';

  return (
    <Link href={link}>
      <button
        className={`w-full h-[6.4rem] rounded-[2rem] px-[2rem] text-custom-black bg-custom-light-gray flex justify-between items-center ${textStyle}`}
        {...props}
      >
        {label}
        <ChevronRightIcon className="w-[1.6rem] h-[1.6rem] text-custom-medium-gray" />
      </button>
    </Link>
  );
}
