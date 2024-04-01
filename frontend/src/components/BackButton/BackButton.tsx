'use client';

import { ChevronLeftIcon } from '@heroicons/react/24/solid';
import { useRouter } from 'next/navigation';

interface BackButtonProps {
  label: string;
}

export default function BackButton({ label, ...props }: BackButtonProps) {
  const router = useRouter();
  return (
    <nav className="flex gap-[0.5rem]">
      <ChevronLeftIcon
        className="w-[2.8rem] h-[2.8rem] text-custom-purple cursor-pointer"
        onClick={() => router.back()}
        {...props}
      />
      <span className="custom-bold-text text-custom-black">{label}</span>
    </nav>
  );
}
