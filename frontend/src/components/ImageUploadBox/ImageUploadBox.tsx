'use client';

import { useState } from 'react';
import Image from 'next/image';
import { CameraIcon } from '@heroicons/react/24/solid';

interface ImageUploadBoxProps {
  text: string;
}

export default function ImageUploadBox({ text: label }: ImageUploadBoxProps) {
  const [image, setImage] = useState<File | null>(null);

  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedImage = e.target.files && e.target.files[0];
    if (selectedImage) {
      setImage(selectedImage);
    }
  };

  const ImageUploadInput = (
    <input
      type="file"
      id="imageUploadInput"
      accept="image/*"
      className="hidden"
      onChange={handleImageUpload}
    />
  );

  return (
    <div
      className="flex justify-center items-center w-full h-[15.2rem]
      border-dashed border-[0.2rem] border-custom-medium-gray"
    >
      {image ? (
        <div className="flex flex-col justify-evenly items-center gap-2 m-4">
          <label
            htmlFor="imageUploadInput"
            className="custom-medium-text text-custom-purple underline cursor-pointer"
          >
            다시 업로드하기
            {ImageUploadInput}
          </label>
          <Image
            src={URL.createObjectURL(image)}
            width={200}
            height={200}
            alt="업로드 이미지"
            className="size-fit max-h-[10rem]"
          />
        </div>
      ) : (
        <label
          htmlFor="imageUploadInput"
          className="flex flex-col items-center gap-[1.2rem] cursor-pointer"
        >
          <CameraIcon className="size-[3.2rem] fill-custom-purple" />
          <p className="custom-medium-text text-custom-purple">{label}</p>
          {ImageUploadInput}
        </label>
      )}
    </div>
  );
}
